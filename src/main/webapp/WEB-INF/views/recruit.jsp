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
    <link rel="stylesheet" href="../../resources/css/recruit.css">

    <!-- JS import -->
    <script type="text/javascript" src="../../resources/js/recruit.js"></script>

    <title>A-Supporter</title>
</head>
<body onload="outputScreen()">

<header id="header">
</header>

<section class="section" id="section">
    <div class="title">모집공고</div>
    <article class="article">
        <div class="top">
            <ul>
                <li><a href="/recruit" >전체</a></li>
                <li><a href="/recruit/orchestra" >교향악단</a></li>
                <li><a href="/recruit/choir" >합창</a></li>
                <li><a href="/recruit/other" >기타</a></li>
            </ul>
            <form action="/recruit" class="search" method="get">
                <div class="bottom_line">
                    <input type="text" name="search" placeholder="검색어를 입력해주세요."/>
                    <button class="fa-solid fa-magnifying-glass"></button>
                </div>
            </form>
        </div>
        <div class="board">

        </div>

        <div class="paging">
            <div class="ctrlBack">
                <a href="" id="leftStart">
                    <i class="arrow fa-solid fa-angles-left"></i>
                </a>
                <a href="" id="left" onclick="leftClick()">
                    <i class="arrow fa-solid fa-angle-left"></i>
                </a>
                <div class="pageNum" id="pageNum"></div>
                <a href="" id="right" onclick="rightClick()">
                    <i class="arrow fa-solid fa-angle-right"></i>
                </a>
                <a href="" id="rightEnd">
                    <i class="arrow fa-solid fa-angles-right"></i>
                </a>
            </div>
        </div>
    </article>
</section>

<footer id="footer">
    <div>문의 : chorales@naver.com</div>
</footer>


<script>
    const getCountPage = ${countPage}
    const countPage = Math.ceil(getCountPage / 10);
    let pageNumber = "";

    const URLSearch = new URLSearchParams(location.search);
    let getQueryString;
    const rightClick = () => {
        if(URLSearch == 0) {
            getQueryString = "1";
        } else if (URLSearch.get('id')>=countPage){
            getQueryString = countPage-1;
        } else {
            getQueryString = URLSearch.get('id');
        }
        document.getElementById('right').setAttribute('href',  'http://localhost:8080/recruit?id='+(Number(getQueryString)+1));
    }

    const leftClick = () => {
        if(URLSearch == 0 || URLSearch.get('id') == 1) {
            getQueryString = "2";
        } else {
            getQueryString = URLSearch.get('id');
        }
        document.getElementById('left').setAttribute('href',  'http://localhost:8080/recruit?id='+(Number(getQueryString)-1));
    }

    let selectColor;
    if(URLSearch == 0) {
        selectColor = "1";
    } else {
        selectColor = URLSearch.get('id');
    }
    if(URLSearch.get('id') < 6) {
        for(let i = 1 ; i <= countPage ; i++) {
            pageNumber +=
                `<form action="/recruit" method="get">
                    <input type="hidden" name="id" value="`+i+`">
                    <button id="pageButton`+i+`">`+i+`</button>
                </form>`;
        }
    } else {
        for(let i = URLSearch.get('id')-4 ; i <= URLSearch.get('id') ; i++) {
            pageNumber +=
                `<form action="/recruit" method="get">
                    <input type="hidden" name="id" value="`+i+`">
                    <button id="pageButton`+i+`">`+i+`</button>
                </form>`;
        }
    }

    document.getElementById('leftStart').setAttribute('href', 'http://localhost:8080/recruit?id=1');
    document.getElementById('rightEnd').setAttribute('href', 'http://localhost:8080/recruit?id='+countPage);
    document.getElementById('pageNum').innerHTML = pageNumber;

    const desktopMain =
        `
            <table>
                <thead>
                    <tr>
                        <td>번호</td>
                        <td>단체명</td>
                        <td>제목</td>
                        <td>게시일</td>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${pageList !=null}">
                        <c:forEach items="${pageList}" var="pageList">
                        <tr>
                            <td>${pageList.id}</td>
                            <td>${pageList.cname}</td>
                            <td><a href="${pageList.url}" target="_blank">${pageList.title}<a></td>
                            <td>${pageList.date}</td>
                        </tr>
                        </c:forEach>
                    </c:if>
                </tbody>
            </table>
        `

    const mobileMain =
        `
            <table>
                <thead>
                    <tr>
                        <td>단체명</td>
                        <td>제목</td>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${pageList !=null}">
                        <c:forEach items="${pageList}" var="pageList">
                        <tr>
                            <td>${pageList.cname}</td>
                            <td rowspan='2'>${pageList.title}</td>
                        </tr>
                        <tr>
                            <td>${pageList.date}</td>
                        </tr>
                        </c:forEach>
                    </c:if>
                </tbody>
            </table>
        `

    const inputScreen = () => {
        let innerWidth = window.innerWidth;
        if( innerWidth < 1182 ) {
            document.getElementById('header').innerHTML = mobileHeader;
            document.querySelector('.board').innerHTML = mobileMain;
        } else {
            document.getElementById('header').innerHTML = desktopHeader;
            document.querySelector('.board').innerHTML = desktopMain;
        }
    }

    const outputScreen = () => {
        inputScreen();
        document.getElementById('pageButton'+selectColor).style.border = "2px solid rgb(170, 169, 169)";

    };

    window.onresize = function() {
        inputScreen();
    }

    const clickMenu = () => {
        if(document.getElementById('header2').style.display == "flex") {
            document.getElementById('header2').style.display="none";
        } else {
            document.getElementById('header2').style.display="flex";
        }

    }
</script>
</body>
</html>