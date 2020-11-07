<%@page import="ec.edu.espoch.academico.Periodo"%>
<%@page import="dda.silabo.reportes.iu.CarrerasIU"%>
<%@page import="dda.silabo.reportes.iu.EntidadIU"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="dda.silabo.datosgenerales.iu.DatosGeneralesIU"%>
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%@page import="dda.silabo.menulateral.MenuLateralIU"%>
<%@page import="dda.silabo.silabo.comunes.Silabo"%>
<%@page import="dda.silabo.datosdocentes.iu.DatosDocentesIU"%>
<%@page import="com.google.gson.Gson"%>
<%
    dda.silabo.sw.SilaboSW_Service service = new dda.silabo.sw.SilaboSW_Service();
    dda.silabo.sw.SilaboSW port = service.getSilaboSWPort();
    String opcion = request.getParameter("opcion");
    Gson G = new Gson();
    String codCarrera = (String) session.getAttribute("codCarrera");
    String codFacultad = (String) session.getAttribute("codFacultad");
    if (opcion.equals("mallaCarrera")) {
        EntidadIU entidadIU = (EntidadIU) session.getAttribute("EntidadesInstitucion");
        if (entidadIU == null) {
            String uAcademica = port.unidadesAcademicasInstitucion();
            entidadIU = G.fromJson(uAcademica, EntidadIU.class);
            session.setAttribute("EntidadesInstitucion", entidadIU);
        }
        codCarrera = entidadIU.getPrimerObjetoCarrera(codFacultad, codCarrera);
        String jsonAsignaturasCarrera = port.unidadesAcademicasAsignaturas(codCarrera);
        CarrerasIU carrerasIU = G.fromJson(jsonAsignaturasCarrera, CarrerasIU.class);
        session.setAttribute("CarreraIU", carrerasIU);
        response.sendRedirect("EstudianteControlador.jsp?opcion=show&vista=todos");
    }

%>  