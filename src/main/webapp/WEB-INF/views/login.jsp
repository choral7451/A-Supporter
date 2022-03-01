<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
     
    <!-- 이모티콘  CDN -->
    <script src="https://kit.fontawesome.com/6dca3e9c71.js" crossorigin="anonymous"></script>

   
    <!-- 폰트 설정 CDN -->
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@900&display=swap" rel="stylesheet">
    
    <!-- CSS import -->
    <link rel="stylesheet" href="../../resources/css/header.css">
    <link rel="stylesheet" href="../../resources/css/index.css">
    <link rel="stylesheet" href="../../resources/css/footer.css">
    <link rel="stylesheet" href="../../resources/css/login.css">
   
    <!-- JS import -->
    <script type="text/javascript" src="../../resources/js/login.js"></script>
    <title>A-Supporter</title>
</head>
<body onload="outputScreen()">
    
    <header id="header">
    </header>
    
    <section class="section" id="section">
        <div class="title">로그인</div>
        <article class="article" id="article">
            <form action="/login.do" method="get">
                <div class="inputBack">
                    <label>아이디</label>
                    <input type="text" placeholder="ID">
                    <label>비밀번호</label>
                    <input type="password" placeholder="Password">
                </div>
                <div class="buttonBack">
                    <button class="loginBtn">로그인</button>
                    <a href="">회원가입</a>
                    <a href="">아이디 찾기</a>
                    <a href="">비밀번호 찾기</a>
                </div>
            </form>
        </article>   
    </section>
    
    <footer id="footer">
        <div>문의 : chorales@naver.com</div>
    </footer>

</body>
</html>