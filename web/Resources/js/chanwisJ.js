$(document).ready(function () {
  
   load_content_page(1);
   
   function load_content_page(ind){
       
       $.ajax({
          url:'productocontroller',
          data:{
              txtProceso : 'jx_productos',
              categoria : ind
          },
          type: 'POST',
          success : function(res){
            var result = eval('(' + res +')');
            var categorialist = $("#ul_categorialist");
            categorialist.empty();
            var vs_active = "active";
            var vs_html="";
            console.log(result);
            $.each(result["data"], function () {
                vs_html = '<li class="tm-paging-item"><a href="#" class="tm-paging-link '+vs_active+'">'+this["descrip"]+'</a></li>';
                categorialist.append(vs_html);
                vs_active = "";
            });
          }
       });
   }
   
   $('#btn_sol_clave').click(function(){
        var txtcorreo = $('#txtcorreo').val();
        $.ajax({
           url: 'usercontroller',
           data : {
               correo : txtcorreo,
               txtProceso : 'json_recuperar_contrasena'
           },
           type : 'POST',
           success: function(res){
               var result = eval('(' +res +')');
               var ind = result['data']['ind'];
               var msj = result['data']['msj'];

               if(ind == 2){
                   $('#txterror').text(msj);
               }else{
                   $('#txterror').text('correo enviado');
               }
           }

        });

   });
   
});


