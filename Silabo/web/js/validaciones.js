/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function soloLetras(e) {
    key = e.keyCode || e.which; 
    tecla = String.fromCharCode(key).toLowerCase(); 
    letras = " áéíóúÁÉÍÓÚabcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZ´1234567890:";
    especiales = [8, 39, 44, 46, 59];
    
    tecla_especial = false;
    for (var i in especiales) {
        if (key == especiales[i]){
            tecla_especial = true;
            break;
        }
    }
    
    if (letras.indexOf(tecla) == -1 && !tecla_especial)
        return false; 
}