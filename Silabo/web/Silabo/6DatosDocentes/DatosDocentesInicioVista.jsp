<%@page import="dda.silabo.roles.iu.RolIU"%>
<%@page import="dda.silabo.reportes.iu.CarrerasIU"%>
<%@page import="dda.silabo.reportes.iu.EntidadIU"%>
<%@page import="dda.silabo.datosdocentes.iu.RolUsuarioIU"%>
<%@page import="dda.silabo.datosgenerales.iu.DatosGeneralesIU"%>
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%@page import="dda.silabo.menulateral.MenuLateralIU"%>
<%@page import="dda.silabo.silabo.comunes.Silabo"%>
<%@page import="dda.silabo.datosdocentes.iu.DatosDocentesIU"%>
<%@page import="dda.silabo.areadocentes.comunes.DocentesInformacion"%>
<%@page import="com.google.gson.Gson"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    String tipo = "";
    if (logueo != null) {
        tipo = "html";
    } else {
        tipo = "login/loginControlador.jsp";
    }
    String codFacultad = (String) session.getAttribute("codFacultad");
    String codMateria = (String) session.getAttribute("codMateria");
    String codCarrera = (String) session.getAttribute("codCarrera");
    String codPeriodo = (String) session.getAttribute("codPeriodo");
    DatosGeneralesIU datosgenerales = (DatosGeneralesIU) session.getAttribute("DatosGenerales_" + logueo.getCedula() + codCarrera + codMateria);
    EntidadIU uAcademica = (EntidadIU) session.getAttribute("Entidad" + logueo.getRolActivo() + logueo.getCedula());
    CarrerasIU carrerasIU = (CarrerasIU) session.getAttribute("CarreraIU" + logueo.getRolActivo() + logueo.getCedula() + codCarrera);
    String menuLateralS = "", nombreDocente = "", contenidoInfo = "", menuTipo = "";
    MenuLateralIU menuLateral = (MenuLateralIU) session.getAttribute("MenuLateral");
    if (menuLateral != null) {
        menuLateralS = menuLateral.toHTMLDocente(0, logueo.getRolActivo());
    }
    DatosDocentesIU rcrHTML = (DatosDocentesIU) session.getAttribute("DatosDocente");
    if (rcrHTML != null) {
        nombreDocente = rcrHTML.nombresDtoHTML();
    }
    RolUsuarioIU rolIu = new RolUsuarioIU();
    RolIU rolIU = (RolIU) session.getAttribute("RolIU");
    if (rolIU != null) {
        contenidoInfo = rolIU.contenidoInfoHTML("trabajos", codMateria, codCarrera, codPeriodo);
        menuTipo = rolIu.toHTMLMenuTipo(logueo.getRoles(), logueo.getRolActivo(), rolIU.getOpciones());
    }
    EntidadIU entidadIU = new EntidadIU();

    String result = "{"
            + "\"nombresDocente\":\"" + nombreDocente + "\","
            + "\"contenidoDinamico\":\"" + datosgenerales.toHTML() + "\","
            + "\"contenidoInfo\":\"" + contenidoInfo + "\","
            + "\"menuTipo\":\"" + menuTipo + "\","
             + "\"rolActivo\":\"" + rolIu.toHTMLRolActivo(logueo.getRoles(), logueo.getRolActivo()) + "\","
            + "\"nombreDocente\":\"" + entidadIU.nombreDocenteToHTML(logueo) + "\","
            + "\"contenidoRoles\":\"" + rolIu.toHTMLTrabajo(logueo.getRoles()) + "\","
            + "\"facultades\":\"" + uAcademica.toHTMLfacultades(codFacultad) + "\","
            + "\"carreras\":\"" + uAcademica.toHTMLcarrerasF(codFacultad, codCarrera) + "\","
            + "\"materias\":\"" + carrerasIU.toHTMLmateriasF(codMateria, logueo.getRolActivo()) + "\","
            + "\"nombreMateria\":\"" + carrerasIU.getMateriaActual(codMateria) + "\","
            + "\"contenidoTitulo\":\"" + datosgenerales.getTitulo() + "\","
            + "\"idSilabo\":\"" + session.getAttribute("idSilabo") + "\","
            + "\"rolActivo\":\"" + rolIu.toHTMLRolActivo(logueo.getRoles(), logueo.getRolActivo()) + "\","
            + "\"tipo\":\"" + tipo + "\","
            + "\"ayuda\":\"" + datosgenerales.getAyuda() + "\","
            + "\"contenidoMenuLateral\":\"" + menuLateralS + "\""
            + "}";
    response.setContentType("text/plain");
    response.getWriter().write(result);
%>