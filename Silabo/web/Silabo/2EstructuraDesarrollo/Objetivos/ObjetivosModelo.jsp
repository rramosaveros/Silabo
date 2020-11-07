<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%@page import="dda.silabo.menulateral.MenuLateralIU"%>
<%@page import="dda.silabo.estructura.unidad.objetivos.iu.ObjetivosIU"%>
<%@page import="com.google.gson.Gson"%>
<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    if (logueo == null) {
        response.sendRedirect("../../../login/loginControlador.jsp");
    } else {
        dda.silabo.sw.SilaboSW_Service service = new dda.silabo.sw.SilaboSW_Service();
        dda.silabo.sw.SilaboSW port = service.getSilaboSWPort();
        String opcion = request.getParameter("opcion");
        Gson G = new Gson();
        try {
            if (opcion.equals("getObjetivos")) {
                String jsonSilabo = (String) session.getAttribute("jsonSilabo");
                String jsonObjetivos = port.objetivoCargar(jsonSilabo);
                ObjetivosIU objetivos = G.fromJson(jsonObjetivos, ObjetivosIU.class);
                session.setAttribute("Objetivos", objetivos);
                response.sendRedirect("ObjetivosControlador.jsp?opcion=show&accion=view");
            }
            if (opcion.equals("saveObjetivo")) {
                String jsonMenuSilabo = "";
                jsonMenuSilabo = (String) session.getAttribute("jsonAsignaturaInfo");
                String jsonObjetivo = (String) session.getAttribute("jsonObjetivo");
                String resultado = port.objetivoGuardar(jsonObjetivo).toString();
                if (resultado.equals("ok")) {
                    ObjetivosIU objetivos = G.fromJson(jsonObjetivo, ObjetivosIU.class);
                    resultado = port.objetivoCargar(G.toJson(objetivos.getSilabos()));
                } else {
                    response.sendError(300, "Error al guardar");
                }
                ObjetivosIU objetivos = G.fromJson(resultado, ObjetivosIU.class);
                session.setAttribute("Objetivos", objetivos);
                String jsonMenuLateral = port.menuSilaboCargar(jsonMenuSilabo);
                MenuLateralIU menulateral = G.fromJson(jsonMenuLateral, MenuLateralIU.class);
                session.setAttribute("MenuLateral", menulateral);
                response.sendRedirect("ObjetivosControlador.jsp?opcion=show&accion=save");
            }
        } catch (Exception e) {
            Logger.getLogger("ObjetivosModelo.jsp").log(Level.SEVERE, "Silabo/2EstructuraDesarrollo/Objetivos/ObjetivosModelo.jsp", e.getClass().getName() + "*****" + e.getMessage());
            System.err.println("ERROR: " + e.getClass().getName() + "***" + e.getMessage());
        }
    }
%>
