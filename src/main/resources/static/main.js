$('document').ready(function(){
    $('.table #editButton').on('click',function(event){

        event.preventDefault();

        var href=$(this).attr('href');

        $.get(href, function (partenaire, status) {
            $('#change-id').val(partenaire.id);
            $('#change-nom').val(partenaire.nom);
            $('#change-adresse').val(partenaire.adresse);
            $('#change-mail').val(partenaire.mail);
            $('#change-tel').val(partenaire.tel);
            $('#change-ninea').val(partenaire.ninea);


        })

        $('#editModal').modal();
    });
});