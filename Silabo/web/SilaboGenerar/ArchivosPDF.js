/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    $(function () {
        var url = sessionStorage.getItem('url');
        $("#archivopdf").html("<embed src='" + url + "' type='application/pdf' style='width: 100%; height: 720px;'>");
    });
});


