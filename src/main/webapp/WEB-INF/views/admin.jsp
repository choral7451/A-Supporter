<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
    <link rel="stylesheet" href="../../resources/css/admin.css">
   
    <!-- JS import -->
    <script type="text/javascript" src="../../resources/js/admin.js"></script>

    <title>A-Supporter</title>
</head>
<body onload="outputScreen()">
    
    <header id="header">
    </header>
    <section class="section" id="section">
        <div class="title">관리자</div> 
        <article class="article">
            <div class="board">
                <table>
                    <thead>
                        <tr>
                            <td>번호</td>
                            <td>분류</td>
                            <td>단체명</td>
                            <td>제목</td>
                            <td>게시일</td>
                            <td></td>
                        </tr>

                    </thead>
                    <tbody>
                    <c:if test="${list != null}">
                        <c:forEach items="${list}" var="list">
                            <tr>
                                <td>${list.id}</td>
                                <td>${list.type}</td>
                                <td>${list.cname}</td>
                                <td><a href="${list.url}" target="_blank">${list.title}<a></a></td>
                                <td>${list.date}</td>
                                <td>
                                    <form action="/admin/update">
                                        <input type="hidden" name="id" value="${list.id}"/>
                                        <button>수정</button>
                                    </form>
                                    <form action="/admin/delete.do" method="get">
                                        <input type="hidden" name="id" value="${list.id}"/>
                                        <button>삭제</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    </tbody>
                </table>
            </div>

            <div class="ctrl">
                <form action="/admin/crawler.do" method="get">
                    <button>크롤링 실행</button>
                </form>
                <form action="/admin/save.do" method="get">
                    <button>저장</button>
                </form>
            </div>
        </article>
    </section>
    
    <footer id="footer">
        <div>문의 : chorales@naver.com</div>
    </footer>
</body>
</html>