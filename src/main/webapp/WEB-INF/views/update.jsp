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
    <link rel="stylesheet" href="../../resources/css/update.css">
   
    <!-- JS import -->
    <script type="text/javascript" src="../../resources/js/admin.js"></script>

    <title>A-Supporter</title>
</head>
<body onload="outputScreen()">
    
    <header id="header">
    </header>
    <section class="section" id="section">
        <div class="title">관리자 수정</div>
        <article class="article">
            <form action="/admin/update.do" class="contentBack" method="post">
                <input type="hidden" name="id" value="${list[0].id}">
                <div class="top">
                    <div class="typeBack">
                        <p>분류</p>
                        <select name="type">
                            <option>분류</option>
                            <option value="합창단">합창단</option>
                            <option value="교향악단">교향악단</option>
                            <option value="행정">행정</option>
                            <option value="기타">기타</option>
                        </select>
                    </div>
                    <div class="cnameBack">
                        <p>단체명</p>
                        <input type="text" name="cname" value="${list[0].cname}"/>
                    </div>
                    <div class="dateBack">
                        <p>게시일</p>
                        <input type="text" name="date" value="${list[0].date}"/>
                    </div>
                </div>
                <div class="center">
                    <div class="titleBack">
                        <p>제목</p>
                        <input type="text" name="title" value="${list[0].title}"/>
                    </div>
                    <div class="urlBack">
                        <p>URL</p>
                        <textarea name="url">${list[0].url}</textarea>
                    </div>
                </div>
                <div class="saveBack">
                    <button>저장</button>
                </div>
            </form>
            <form action="/admin/cancel.do" class="cancelBack"><button>취소</button></form>
        </article>
    </section>
    
    <footer id="footer">
        <div>문의 : chorales@naver.com</div>
    </footer>
</body>
</html>