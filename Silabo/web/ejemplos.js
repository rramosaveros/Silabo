//$(document).ready(function () {
//    $(".todolist").focus(function () {
//        if (document.getElementById('todolist').value === '') {
//            alert("libre");
//            document.getElementById('todolist').value += '• ';
//        }
//    });
//    $(".todolist").keyup(function (event) {
//        var keycode = (event.keyCode ? event.keyCode : event.which);
//        if (keycode == '13') {
//            var txtval = document.getElementById('todolist').value;
//            var lineas = txtval.split("\n");
//            var p = (lineas.length) - 2;
//            (lineas[p].val("SASADSADSAD"));
//            document.getElementById('todolist').value += '• ';
////            $('#todolist').css('background-color', '#000000');
//
//        }
////        var txtval = document.getElementById('todolist').value;
////        if (txtval.substr(txtval.length - 1) == '\n') {
////            alert("salto");
////            document.getElementById('todolist').value = txtval.substring(0, txtval.length - 1);
////        }
//    });
//    $('input[type=button]').on('click', function () {
//        var cursorPos = $('#todolist').prop('selectionStart');
//        var v = $('#todolist').val();
//        var textBefore = v.substring(0, cursorPos);
//        var textAfter = v.substring(cursorPos, v.length);
//        $('#todolist').val(textBefore + $(this).val() + textAfter);
//    });
//});

function agregar() {
    var cursorPosStart = $('#todolist').prop('selectionStart');
    var cursorPosEnd = $('#todolist').prop('selectionEnd');
    var v = $('#todolist').val();
    var textBefore = v.substring(0, cursorPosStart);

    var textAfter = v.substring(cursorPosEnd, v.length);
    var s = v.substring(cursorPosStart, cursorPosEnd);
    if (s.length > 1) {
        var lineas = s.split("\n");
        var sF = '';
        var cont = 0;
        for (var i = 0; i < lineas.length; i++) {
            var contiene = lineas[i].indexOf('•');
            if (contiene === -1) {
                sF += '• ' + lineas[i] + '\n';
            } else {
                cont++;
                sF += lineas[i] + '\n';
            }
        }
        if (cont === (lineas.length)) {
            sF = s.replace(/• /gi, '');
            sF = sF.trim();
        }
        $('#todolist').val(textBefore + sF + textAfter);
        eliminarfilasInnecesarias();
    }
}

function identar() {
    var cursorPosStart = $('#todolist').prop('selectionStart');
    var cursorPosEnd = $('#todolist').prop('selectionEnd');
    var v = $('#todolist').val();
    var textBefore = v.substring(0, cursorPosStart);
    var textAfter = v.substring(cursorPosEnd, v.length);
    var s = v.substring(cursorPosStart, cursorPosEnd);
    var lineas = s.split("\n");
    var sF = '';
    for (var i = 0; i < lineas.length; i++) {
        sF += '   ' + lineas[i] + '\n';
    }
    $('#todolist').val(textBefore + sF + textAfter);
    eliminarfilasInnecesarias();
}

function desidentar() {
    var cursorPosStart = $('#todolist').prop('selectionStart');
    var cursorPosEnd = $('#todolist').prop('selectionEnd');
    var v = $('#todolist').val();
    var textBefore = v.substring(0, cursorPosStart);
    var textAfter = v.substring(cursorPosEnd, v.length);
    var s = v.substring(cursorPosStart, cursorPosEnd);
    var lineas = s.split("\n");
    var sF = '';
    for (var i = 0; i < lineas.length; i++) {
        var st = lineas[i];
        st = st.trim();
        sF += st + '\n';
    }
    $('#todolist').val(textBefore + sF + textAfter);
    eliminarfilasInnecesarias();
}

function eliminarfilasInnecesarias() {

    var v = $('#todolist').val();

    var lineas = v.split("\n");
    var sF = '';
    for (var i = 0; i < lineas.length - 1; i++) {
        var st = lineas[i];
        if (st.length > 2 || lineas[i + 1].length > 2) {
            sF += st + '\n';
        }
    }
    $('#todolist').val(sF);
}