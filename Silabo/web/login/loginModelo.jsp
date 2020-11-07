<%@page import="dda.silabo.roles.iu.RolIU"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%
    dda.silabo.sw.SilaboSW_Service service = new dda.silabo.sw.SilaboSW_Service();
    dda.silabo.sw.SilaboSW port = service.getSilaboSWPort();
    String usr = (String) session.getAttribute("cuenta");
    String pass = (String) session.getAttribute("clave");
    Gson G = new Gson();
    String jasonAutenticar = port.autenticarUsuario(usr);
    LoginIU loginUsuario = G.fromJson(jasonAutenticar, LoginIU.class);
    if (loginUsuario.getRolActivo() == null) {
        if (!loginUsuario.getRoles().isEmpty()) {
            loginUsuario.agregarRolActivo(null);
        } else {
            loginUsuario.setRolActivo("Est");
        }

    }
    session.setAttribute("logueo", loginUsuario);
    session.setAttribute("cuenta", null);
    session.setAttribute("clave", null);
//        String result = ("../index.jsp");
    response.sendRedirect("../index.jsp");
//        response.setContentType("text/plain");
//        response.getWriter().write(result);


%>   