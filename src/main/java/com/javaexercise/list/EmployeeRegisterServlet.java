package com.javaexercise.list;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/employees/register")
public class EmployeeRegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

    	String mail = request.getParameter("mail");

    	if (mail == null || mail.isBlank()) {
    	    throw new ServletException("メールアドレスを入力してください。");
    	}

    	if (mail.length() > 255) {
    	    throw new ServletException("メールアドレスは255文字以内で入力してください。");
    	}

    	if (!mail.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
    	    throw new ServletException("正しいメールアドレスを入力してください。");
    	}

    	// パスワードを取得
    	String password = (String) request.getSession().getAttribute("password");

    	if (password == null) {
    	    throw new ServletException("パスワードが取得できませんでした。");
    	}

    	if (password.length() < 8) {
    	    throw new ServletException("パスワードは8文字以上で入力してください。");
    	}

    	if (!password.matches("^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]+$")) {
    	    throw new ServletException("パスワードは英字と数字を両方含めてください。");
    	}

    
        String hashedPassword;

        try {
            hashedPassword = hashPassword(password);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new ServletException(e);
        }
        String name = request.getParameter("name");
        if (name == null || name.isBlank()) {
            throw new ServletException("名前を入力してください。");
        }
        if (name.length() > 255) {
            throw new ServletException("名前は255文字以内で入力してください。");
        }
        String age = request.getParameter("age");
        String profile = request.getParameter("profile");
        if (profile != null && profile.length() > 10000) {
            throw new ServletException("プロフィールは10000文字以内で入力してください。");
        }
        String birthplace = request.getParameter("birthplace");
        String height = request.getParameter("height");
        String weight = request.getParameter("weight");
        
        if (age == null || age.isBlank()) {
            throw new ServletException("年齢を入力してください。");
        }
        int ageValue;

        try {
            ageValue = Integer.parseInt(age);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "年齢は数字で入力してください。");
            request.getRequestDispatcher("/employeeRegister.jsp")
                   .forward(request, response);
            return;
        }
        if (ageValue < 0 || ageValue > 150) {
            throw new ServletException("年齢は0〜150の範囲で入力してください。");
        }
        
        if (height == null || height.isBlank()) {
            throw new ServletException("身長を入力してください。");
        }

        if (weight == null || weight.isBlank()) {
            throw new ServletException("体重を入力してください。");
        }
        
        double heightValue;
        double weightValue;

        try {
            heightValue = Double.parseDouble(height);
            weightValue = Double.parseDouble(weight);
        } catch (NumberFormatException e) {
            throw new ServletException("身長と体重は数字で入力してください。", e);
        }
        if (heightValue < 0 || heightValue > 300) {
            throw new ServletException("身長は0〜300の範囲で入力してください。");
        }
        if (weightValue < 0 || weightValue > 300) {
            throw new ServletException("体重は0〜300の範囲で入力してください。");
        }

        String uid;

        try {
            uid = createUid();
        } catch (NoSuchAlgorithmException e) {
            throw new ServletException(e);
        }
        EmployeeDAO employeeDAO = new EmployeeDAO();
        try {
            employeeDAO.registerEmployee(
                uid,
                mail,
                name,
                hashedPassword,
                ageValue,
                profile,
                birthplace,
                heightValue,
                weightValue
            );

         // 登録成功時の処理
            request.getSession().removeAttribute("password");
            request.getSession().setAttribute("message", "登録が完了しました。");
            request.getSession().setAttribute("messageType", "success");

        } catch (SQLException e) {

        	// 登録失敗時の処理
        	request.getSession().setAttribute("message", "登録が失敗しました。");
        	request.getSession().setAttribute("messageType", "error");
        	e.printStackTrace();

        }

        // 成功・失敗どちらの場合も従業員一覧画面に戻る
        response.sendRedirect(request.getContextPath() + "/employees");
    }

    private String createUid() throws NoSuchAlgorithmException {

        String source = java.util.UUID.randomUUID().toString();

        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        byte[] hash = digest.digest(
                source.getBytes(StandardCharsets.UTF_8)
        );

        StringBuilder uid = new StringBuilder();

        for (byte b : hash) {
            uid.append(String.format("%02x", b));
        }

        return uid.toString();
    }
    private String hashPassword(String password)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
    	byte[] salt = new byte[16];
    	SecureRandom random = new SecureRandom();
    	random.nextBytes(salt);
    	PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 600000, 256);
    	SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
    	byte[] hash;

    	try {
    	    hash = factory.generateSecret(spec).getEncoded();
    	} finally {
    	    spec.clearPassword();
    	}
    	StringBuilder hashText = new StringBuilder();

    	for (byte b : hash) {
    	    hashText.append(String.format("%02x", b));
    	}

    	StringBuilder saltText = new StringBuilder();

    	for (byte b : salt) {
    	    saltText.append(String.format("%02x", b));
    	}

    	return saltText.toString() + ":" + hashText.toString();
    }
}