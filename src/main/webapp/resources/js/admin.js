const mobileHeader = 
`
    <div class="header1">
        <nav>
            <i class="fa-solid fa-bars" onclick="clickMenu()"></i>
        </nav>
        <div class="logo">
            <h1>관리자</h1>
        </div>
        <div class="login"><a href=""><i class="fa-regular fa-user"></i></a></div>
    </div>
    <div id="header2" class="header2"> 
        <li><a href="/">Home</a></li>
        <li><a href="/about">소개</a></li>
        <li><a href="/recruit">모집공고</a></li>
    </div>        
`

const desktopHeader =
`
    <div class="header1">
        <div class="logo">
            <a href="/"><img src="../../resources/img/logo.png" alt=""></a>
        </div>
        <nav>
            <li><a href="/about">소개</a></li>
            <li><a href="/recruit">모집공고</a></li>
        </nav>
        <div class="login"><a href="/login">로그인</a></div>
    </div>
`

const inputScreen = () => {
    let innerWidth = window.innerWidth;
    if( innerWidth < 1181 ) {
        document.getElementById('header').innerHTML = mobileHeader;
    } else {
        document.getElementById('header').innerHTML = desktopHeader;
    }
}

const outputScreen = () => {
    inputScreen();
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





