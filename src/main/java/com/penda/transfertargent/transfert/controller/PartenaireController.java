package com.penda.transfertargent.transfert.controller;

import com.penda.transfertargent.transfert.dao.ICompte;
import com.penda.transfertargent.transfert.dao.IPartenaire;
import com.penda.transfertargent.transfert.dao.IRole;
import com.penda.transfertargent.transfert.dao.IUtilisateur;
import com.penda.transfertargent.transfert.model.Compte;
import com.penda.transfertargent.transfert.model.Partenaire;
import com.penda.transfertargent.transfert.model.Role;
import com.penda.transfertargent.transfert.model.Utilisateur;
import com.penda.transfertargent.transfert.service.PartenaireClientService;
import com.penda.transfertargent.transfert.service.TransComptVerseService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@PreAuthorize("hasAuthority('ROLE_SUPERADMIN')")
@RequestMapping("/api/partenaire")
@Controller
public class PartenaireController {
    private static String UPLOADED_FOLDER = "D://cours//M2//JEE//image//";
    @Autowired
    TransComptVerseService transComptVerseService;

    @Autowired
    private BCryptPasswordEncoder encode;

    @Autowired
    private PartenaireClientService partenaireClientService;

    @Autowired
    private IRole iRole;

    @Autowired
    private IPartenaire iPartenaire;

    @Autowired
    private IUtilisateur iUtilisateur;

    @Autowired
    private ICompte iCompte;

    @RequestMapping(value = "image/{imageName}")
    @ResponseBody
    public byte[] getImage(@PathVariable(value = "imageName") String imageName) throws IOException {
        File serverFile = new File("D://cours//M2//JEE//image//" + imageName + ".jpg");
        return Files.readAllBytes(serverFile.toPath());
    }

    @RequestMapping(value = "image-update/{imageName}")
    @ResponseBody
    public byte[] getImageForUpdate(@PathVariable(value = "imageName") String imageName) throws IOException {
        File serverFile = new File("D://cours//M2//JEE//image//" + imageName);
        return Files.readAllBytes(serverFile.toPath());
    }

    @PreAuthorize("hasAuthority('ROLE_SUPERADMIN')")
    @GetMapping("/")
    public String index(Model model) {
        Partenaire partenaire = new Partenaire();
        List<Utilisateur> users = new ArrayList<>();
        Utilisateur u = new Utilisateur();
        u.setRoles(new ArrayList<>());
        users.add(u);
        partenaire.setUtilisateurList(users);
        model.addAttribute("unPartenaire", partenaire);
        return "addPartenaire";
    }

    @PreAuthorize("hasAuthority('ROLE_SUPERADMIN')")
    @GetMapping("/update")
    public String update(@RequestParam("Id") Integer id, Model model) {
        Partenaire partenaire = partenaireClientService.getOne(id).orElse(null);
        if (partenaire != null) {
            String imgUrl = partenaire.getUtilisateurList().get(0).getPhoto();
            System.out.println(imgUrl);
            model.addAttribute("unPartenaire", partenaire);
            model.addAttribute("imgUrl", imgUrl);
            model.addAttribute("errormailpartenaire", "");
            model.addAttribute("errormailuser", "");
            model.addAttribute("errorninea", "");
            model.addAttribute("error", "");
            return "updatePartenaire";
        } else return "listPartenaire";
    }

    @PreAuthorize("hasAuthority('ROLE_SUPERADMIN')")
    @PostMapping("/update")
    public String updatePartenaire(@ModelAttribute("unPartenaire") Partenaire p, Model model) {
        try {
            Utilisateur utilisateur = p.getUtilisateurList().get(0);
            Utilisateur utilisateur1 = iUtilisateur.findById(p.getUtilisateurList().get(0).getId()).orElse(null);
            if (utilisateur1 != null) {
                byte[] bytes = {};
                Path path = null;
                if (!utilisateur.getFiles()[0].getName().equals("")) {
                    MultipartFile file = utilisateur.getFiles()[0];
                    bytes = file.getBytes();
                    path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
                    utilisateur.setPhoto(file.getOriginalFilename());
                } else {
                    utilisateur.setPhoto("default.jpeg");
                }

                utilisateur1.setPhoto(utilisateur.getPhoto());
                utilisateur1.setFiles(utilisateur.getFiles());
                try {
                    iUtilisateur.save(utilisateur1);
                    if (bytes.length != 0) {
                        Files.write(path, bytes);
                    }
                    return "redirect:/api/partenaire/list";
                } catch (Exception e) {
                    model.addAttribute("error", "Une erreur s'est produite");
                    e.printStackTrace();
                }
            } else return "redirect:/api/partenaire/list";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "updatePartenaire";
    }

    @PreAuthorize("hasAuthority('ROLE_SUPERADMIN')")
    @PostMapping("/add")
    public String index(@ModelAttribute("unPartenaire") Partenaire p, Model model) {
        //vérificatiion du mail partenaire
        Partenaire part = iPartenaire.findByMail(p.getMail());
        boolean cool = true;
        if (part != null) {
            model.addAttribute("errormailpartenaire", "L'adresse email [" + p.getMail() + "] existe déjà chez un partenaire !");
            cool = false;
        }
        Utilisateur user = iUtilisateur.findByEmail(p.getUtilisateurList().get(0).getEmail());
        if (user != null) {
            model.addAttribute("errormailuser", "L'adresse email [" + p.getUtilisateurList().get(0).getEmail() + "] est déja utilisé par un utilisateur !");
            cool = false;
        }
        part = iPartenaire.findByNinea(p.getNinea());
        // cool = true;
        if (part != null) {
            model.addAttribute("errorninea", "Le Ninéa [" + p.getNinea() + "] existe déjà chez un partenaire !");
            cool = false;
        }
        if (!cool) {
            return "addPartenaire";
        }
        try {
            byte[] bytes = {};
            Path path = null;
            if (!p.getUtilisateurList().get(0).getFiles()[0].getName().equals("")) {
                MultipartFile file = p.getUtilisateurList().get(0).getFiles()[0];
                bytes = file.getBytes();
                path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
                p.getUtilisateurList().get(0).setPhoto(file.getOriginalFilename());
            } else {
                p.getUtilisateurList().get(0).setPhoto("default.jpeg");
            }
            Role r = iRole.findRoleByLibRole("ROLE_ADMIN");
            p.getUtilisateurList().get(0).setRoles(new ArrayList<>());
            p.getUtilisateurList().get(0).getRoles().add(r);
            p.getUtilisateurList().get(0).setPwd(encode.encode("passer"));
            String login = p.getUtilisateurList().get(0).getEmail().split("@")[0];
            p.getUtilisateurList().get(0).setLogin(login);
            p.getUtilisateurList().get(0).setActive(false);
            p.getUtilisateurList().get(0).setPartenaire(p);
            // set compte value
            p.setComptes(new ArrayList<>());
            Compte c = new Compte();
            Integer maxid = iCompte.getMaxId();
            maxid = (maxid == null ? 1 : maxid + 1);
            c.setNumero(p.getNinea() + "-" + new DecimalFormat("0000000").format(maxid + 1));
            c.setSolde(0.0f);
            c.setPartenaire(p);
            p.getComptes().add(c);
            p.getUtilisateurList().get(0).setCompte(c);

            try {
                iPartenaire.save(p);
                if (bytes.length != 0) {
                    Files.write(path, bytes);
                }
                return "redirect:/api/partenaire/list";
            } catch (Exception e) {
                model.addAttribute("error", "Une erreur s'est produite");
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "addPartenaire";
    }

    @PreAuthorize("hasAuthority('ROLE_SUPERADMIN')")
    @GetMapping("/list")
    public String getAll(Model model) {
        List<Partenaire> partenaires = partenaireClientService.getAll();
        model.addAttribute("partenaires", partenaires);

        return "listPartenaire";
    }

    @PreAuthorize("hasAuthority('ROLE_SUPERADMIN')")
    @GetMapping("/getOne/{id}")
    @ResponseBody
    public Optional<Partenaire> getOne(@PathVariable Integer id, Model model) {

        return partenaireClientService.getOne(id);
    }

    @PreAuthorize("hasAuthority('ROLE_SUPERADMIN')")
    @PostMapping(value = "/transfert/partenaire/addNew")
    public String addNew(Partenaire partenaire) {
        partenaireClientService.addNew(partenaire);
        return "redirect:/api/partenaire/list";
    }

    @PreAuthorize("hasAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(value = "/transfert/partenaire/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(Partenaire partenaire) {
        partenaireClientService.update(partenaire);
        return "redirect:/api/partenaire/list";
    }

}
