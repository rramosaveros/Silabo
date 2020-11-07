<%@page import="dda.silabo.ayudas.AyudaSilabo"%>
<%
    String opcion = request.getParameter("opcion");
    if (opcion.equals("objetivo")) {
//        AyudaSilabo ayudaSilabo = new AyudaSilabo();
//        String result = "{"
//                + "\"lnkAyuda\":\"" + ayudaSilabo.ayudaObjetivo() + "\""
//                + "}";
//
//        response.setContentType("text/plain");
//        response.getWriter().write(result);
%>
<div class="form-group row" style="text-align: justify;">
    <div class="col-xs-12">
        <table class="table-bordered table-hover">
            <tr style="text-align: center;">
                <td colspan="2"><b>Ayuda de la seccion objetivos</b></td>
            </tr>

            <tr style="text-align: center;">
                <td  style="width: 30%;"><b>Funcionalidad</b></td>
                <td ><b>Descripcion</b></td>
            </tr>
            <tr>
                <td style=" text-align: center;width:30%;">
                    <img src="images/objetivos/btnNuevo.PNG" width="100" height="50" alt="SDA">
                </td>
                <td >
                    Boton que le permitira agregar un cuadro de texto para escribir su objetivo
                </td>
            </tr>
            <tr>
                <td style=" text-align: center;width:30%;">
                    <img src="images/objetivos/txtNuevo.PNG" width="200" height="60" alt="SDA">
                </td>
                <td >
                    Cuadro de texto para escribir el objetivo correspondiente a la unidad
                </td>
            </tr>
        </table>
    </div>
</div>
<%}


%>