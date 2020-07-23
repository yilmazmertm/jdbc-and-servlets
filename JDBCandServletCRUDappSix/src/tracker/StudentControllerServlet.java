package tracker;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@WebServlet(name = "/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {

    @Resource (name = "jdbc/web_student_tracker")
    private DataSource dataSource;

    private StudentDbUtil studentDbUtil;

    @Override
    public void init() throws ServletException {
        super.init();

        try {
            studentDbUtil = new StudentDbUtil(dataSource);
        } catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            String theCommand = request.getParameter("command");

            if(theCommand == null) {
                theCommand = "LIST";
            }

            switch (theCommand) {//list students

                case "ADD":
                    addStudent(request,response);
                    break;
                case "LOAD":
                    loadStudent(request,response);
                    break;
                case "UPDATE":
                    updateStudent(request, response);
                    break;
                case "DELETE":
                    deleteStudent(request, response);
                    break;
                default:
                    listStudents(request, response);
            }
        }
        catch (Exception exc){
            throw new ServletException(exc);
        }
    }

    private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception{

        List<Student> students = studentDbUtil.getStudents();
        request.setAttribute("STUDENT_LIST", students);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
        dispatcher.forward(request, response);
    }

    private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        Student theStudent = new Student(firstName, lastName, email);

        studentDbUtil.addStudent(theStudent);

        listStudents(request, response);
    }

    private void loadStudent (HttpServletRequest request, HttpServletResponse response) throws Exception{
        String theStudentId = request.getParameter("studentId");

        Student theStudent = studentDbUtil.getStudent(theStudentId);
        request.setAttribute("THE_STUDENT", theStudent);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/update-student-form.jsp");
        requestDispatcher.forward(request, response);
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("studentId"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        Student theStudent = new Student(id, firstName, lastName, email);

        studentDbUtil.updateStudent(theStudent);

        listStudents(request, response);

    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String theStudentId = request.getParameter("studentId");
        studentDbUtil.deleteStudent(theStudentId);
        listStudents(request,response);
    }






}
