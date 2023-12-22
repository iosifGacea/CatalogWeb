package com.example.democatalog;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Controller
public class ControllerClass
{
    @GetMapping()
    public String open(Model model) {
        return "index";
    }
    @GetMapping("/StudentLogIn")
    public String home(Model model) {
        model.addAttribute("student", new Student());
        return "StudentLogIn";
    }
    @GetMapping("/TeacherLogIn")
    public String _home(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "TeacherLogIn";
    }
    @GetMapping("/StudentHome")
    public String open(@ModelAttribute Student student, Model model) throws SQLException {
            List<Student> students = Db.findAllStudents();
            String studentsGrade= "";

            for(int i=0;i<students.size();i++) {
                if (Objects.equals(students.get(i).name, student.getName())  && Objects.equals( students.get(i).password, student.getPassword()) ){
                    student.id=students.get(i).id;

                    List<Grade> grades = Db.findAllGradesForStudent(student.id);
                    for(Grade grade : grades)
                    {
                        studentsGrade += (grade.subject.name + " " + grade.grade + '\n');
                    }
                    model.addAttribute("grades", studentsGrade);
                    model.addAttribute("student", student);
                    return "studentHome";
                }
            }

        model.addAttribute("student", new Student());
        return "StudentLogIn";
    }
    @GetMapping("/TeacherHome")
    public String _open(@ModelAttribute Teacher teacher, Model model) throws SQLException{
        List<Teacher> teachers = Db.findAllTeachers();

        for(Teacher value : teachers)
        {
            if(Objects.equals(value.name, teacher.name) && Objects.equals(value.password, teacher.password))
            {
                teacher.id = value.id;

                model.addAttribute("myTouple", new MyTouple());
                model.addAttribute("teacher", teacher);
                return "teacherHome";
            }
        }

        model.addAttribute("teacher", new Teacher());
        return "TeacherLogIn";
    }
    @GetMapping("/TeacherAddGrade")
    public String teacherAddGrade(@ModelAttribute MyTouple myTouple,@ModelAttribute Teacher teacher, Model model)throws SQLException{
        int id_student = 0, id_subject  = 0;
        List<Student> students=Db.findAllStudents();
        List<Subject> subjects=Db.findAllSubjects();

        for(Student s : students)
        {
            if(Objects.equals(s.name, myTouple.first))
                id_student = s.id;
        }
        for(Subject s : subjects)
        {
            if(Objects.equals(s.name, myTouple.second))
                id_subject = s.id;
        }

        String SQLCode = "insert into grade(id_student, id_subject, grade, date) value(" +
                id_student + ", " + id_subject + ", " + myTouple.third + ", current_date())";
        Connection _con = DriverManager.getConnection("jdbc:mysql://localhost:3306/catalog", "root", "123456");
        Statement statement = _con.createStatement();
        int nr = statement.executeUpdate(SQLCode);
        if (nr > 0) {
            System.out.println("ROW INSERTED");
        } else {
            System.out.println("ROW NOT INSERTED");
        }
        return "teacherHome";
    }

}
