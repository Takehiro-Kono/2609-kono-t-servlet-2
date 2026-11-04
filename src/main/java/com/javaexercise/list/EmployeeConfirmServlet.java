package com.javaexercise.list;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/employees/confirm")
public class EmployeeConfirmServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {
    	// 修正ボタンが押された場合
    	if ("true".equals(request.getParameter("edit"))) {

    	    request.setAttribute("mail", request.getParameter("mail"));
    	    request.setAttribute("name", request.getParameter("name"));
    	    request.setAttribute("age", request.getParameter("age"));
    	    request.setAttribute("profile", request.getParameter("profile"));
    	    request.setAttribute("birthplace", request.getParameter("birthplace"));
    	    request.setAttribute("height", request.getParameter("height"));
    	    request.setAttribute("weight", request.getParameter("weight"));

    	    request.getRequestDispatcher("/employeeCreate.jsp")
    	           .forward(request, response);

    	    return;
    	}

        // フォームから送られてきた値を受け取る
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");
        String passwordConfirm = request.getParameter("passwordConfirm");
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        String profile = request.getParameter("profile");
        String birthplace = request.getParameter("birthplace");
        String height = request.getParameter("height");
        String weight = request.getParameter("weight");

     // パスワードの入力チェック
     if (password == null || password.isBlank()) {
         request.setAttribute("passwordError", "パスワードを入力してください。");
         setFormValues(request, mail, name, age, profile, birthplace, height, weight);

         request.getRequestDispatcher("/employeeCreate.jsp")
                .forward(request, response);
         return;
     }
     if (!java.util.Objects.equals(password, passwordConfirm)) {
    	    request.setAttribute("passwordConfirmError", "パスワードが一致しません");
    	    setFormValues(request, mail, name, age, profile, birthplace, height, weight);
    	    request.getRequestDispatcher("/employeeCreate.jsp").forward(request, response);
    	    return;
    	}

     if (password.length() < 8) {
         request.setAttribute("passwordError", "パスワードは8文字以上で入力してください。");
         setFormValues(request, mail, name, age, profile, birthplace, height, weight);

         request.getRequestDispatcher("/employeeCreate.jsp")
                .forward(request, response);
         return;
     }

     if (!password.matches("^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]+$")) {
         request.setAttribute("passwordError", "パスワードは英字と数字を両方含めてください。");
         setFormValues(request, mail, name, age, profile, birthplace, height, weight);

         request.getRequestDispatcher("/employeeCreate.jsp")
                .forward(request, response);
         return;
     }
     if (!password.equals(passwordConfirm)) {
    	    request.setAttribute("passwordConfirmError", "パスワードが一致しません");
    	    setFormValues(request, mail, name, age, profile, birthplace, height, weight);
    	    request.getRequestDispatcher("/employeeCreate.jsp").forward(request, response);
    	    return;
    	}

     // 入力チェックを通過したらセッションに保存
     request.getSession().setAttribute("password", password);
       
        if (age == null || age.isBlank()) {
            request.setAttribute("ageError", "年齢を入力してください。");
            request.setAttribute("mail", mail);
            request.setAttribute("name", name);
            request.setAttribute("age", age);
            request.setAttribute("profile", profile);
            request.setAttribute("birthplace", birthplace);
            request.setAttribute("height", height);
            request.setAttribute("weight", weight);

            request.getRequestDispatcher("/employeeCreate.jsp")
                   .forward(request, response);
            return;
        }


        try {
            int ageNumber = Integer.parseInt(age);

            if (ageNumber < 0 || ageNumber > 150) {
                request.setAttribute("ageError", "年齢は0〜150歳の範囲で入力してください。");
                request.setAttribute("mail", mail);
                request.setAttribute("name", name);
                request.setAttribute("age", age);
                request.setAttribute("profile", profile);
                request.setAttribute("birthplace", birthplace);
                request.setAttribute("height", height);
                request.setAttribute("weight", weight);

                request.getRequestDispatcher("/employeeCreate.jsp")
                       .forward(request, response);
                return;
            }

        } catch (NumberFormatException e) {

            request.setAttribute("ageError", "年齢は数字で入力してください。");
            request.setAttribute("mail", mail);
            request.setAttribute("name", name);
            request.setAttribute("age", age);
            request.setAttribute("profile", profile);
            request.setAttribute("birthplace", birthplace);
            request.setAttribute("height", height);
            request.setAttribute("weight", weight);

            request.getRequestDispatcher("/employeeCreate.jsp")
                   .forward(request, response);
            return;
        }
        
     // 身長の入力チェック
        if (height == null || height.isBlank()) {
            request.setAttribute("heightError", "身長を入力してください。");
            setFormValues(request, mail, name, age, profile, birthplace, height, weight);

            request.getRequestDispatcher("/employeeCreate.jsp")
                   .forward(request, response);
            return;
        }


try {
    double heightNumber = Double.parseDouble(height);

    if (heightNumber < 0 || heightNumber > 300) {
        request.setAttribute("heightError", "身長は0〜300の範囲で入力してください。");
        setFormValues(request, mail, name, age, profile, birthplace, height, weight);
        request.getRequestDispatcher("/employeeCreate.jsp")
               .forward(request, response);
        return;
    }

} catch (NumberFormatException e) {

            request.setAttribute("heightError", "身長は数字で入力してください。");
            setFormValues(request, mail, name, age, profile, birthplace, height, weight);

            request.getRequestDispatcher("/employeeCreate.jsp")
                   .forward(request, response);
            return;
        }
        
     // メールアドレスの入力チェック
        if (mail == null || mail.isBlank()) {
            request.setAttribute("mailError", "メールアドレスを入力してください。");
            setFormValues(request, mail, name, age, profile, birthplace, height, weight);
            request.getRequestDispatcher("/employeeCreate.jsp")
                   .forward(request, response);
            return;
        }

        if (mail.length() > 255) {
            request.setAttribute("mailError", "メールアドレスは255文字以内で入力してください。");
            setFormValues(request, mail, name, age, profile, birthplace, height, weight);
            request.getRequestDispatcher("/employeeCreate.jsp")
                   .forward(request, response);
            return;
        }

        if (!mail.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            request.setAttribute("mailError", "正しいメールアドレスを入力してください。");
            setFormValues(request, mail, name, age, profile, birthplace, height, weight);
            request.getRequestDispatcher("/employeeCreate.jsp")
                   .forward(request, response);
            return;
        }
        
     // 名前の入力チェック
        if (name == null || name.isBlank()) {
            request.setAttribute("nameError", "名前を入力してください。");
            setFormValues(request, mail, name, age, profile, birthplace, height, weight);

            request.getRequestDispatcher("/employeeCreate.jsp")
                   .forward(request, response);
            return;
        }

        if (name.length() > 255) {
            request.setAttribute("nameError", "名前は255文字以内で入力してください。");
            setFormValues(request, mail, name, age, profile, birthplace, height, weight);

            request.getRequestDispatcher("/employeeCreate.jsp")
                   .forward(request, response);
            return;
        }
        
     // プロフィールの入力チェック
        if (profile == null || profile.isBlank()) {
            request.setAttribute("profileError", "プロフィールを入力してください。");
            setFormValues(request, mail, name, age, profile, birthplace, height, weight);

            request.getRequestDispatcher("/employeeCreate.jsp")
                   .forward(request, response);
            return;
        }

        if (profile.length() > 1000) {
            request.setAttribute("profileError", "プロフィールは1000文字以内で入力してください。");
            setFormValues(request, mail, name, age, profile, birthplace, height, weight);

            request.getRequestDispatcher("/employeeCreate.jsp")
                   .forward(request, response);
            return;
        }
     // 体重の入力チェック
        if (weight == null || weight.isBlank()) {
            request.setAttribute("weightError", "体重を入力してください。");
            setFormValues(request, mail, name, age, profile, birthplace, height, weight);

            request.getRequestDispatcher("/employeeCreate.jsp")
                   .forward(request, response);
            return;
        }


try {
    double weightNumber = Double.parseDouble(weight);

    if (weightNumber < 0 || weightNumber > 300) {
        request.setAttribute("weightError", "体重は0〜300の範囲で入力してください。");
        setFormValues(request, mail, name, age, profile, birthplace, height, weight);
        request.getRequestDispatcher("/employeeCreate.jsp")
               .forward(request, response);
        return;
    }

} catch (NumberFormatException e) {

            request.setAttribute("weightError", "体重は数字で入力してください。");
            setFormValues(request, mail, name, age, profile, birthplace, height, weight);

            request.getRequestDispatcher("/employeeCreate.jsp")
                   .forward(request, response);
            return;
        }

        // JSPに値を渡す
        request.setAttribute("mail", mail);
        request.setAttribute("name", name);
        request.setAttribute("age", age);
        request.setAttribute("profile", profile);
        request.setAttribute("birthplace", birthplace);
        request.setAttribute("height", height);
        request.setAttribute("weight", weight);

        // 確認画面を表示
        request.getRequestDispatcher("/employeeConfirm.jsp")
               .forward(request, response);
    }
 // 入力値をJSPに渡す共通処理
    private void setFormValues(
            HttpServletRequest request,
            String mail,
            String name,
            String age,
            String profile,
            String birthplace,
            String height,
            String weight) {

        request.setAttribute("mail", mail);
        request.setAttribute("name", name);
        request.setAttribute("age", age);
        request.setAttribute("profile", profile);
        request.setAttribute("birthplace", birthplace);
        request.setAttribute("height", height);
        request.setAttribute("weight", weight);
    }
}
