<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="properties.pagecontent"/>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
</head>
<footer class="text-center text-lg-start bg-light text-muted">
    <section class="d-flex justify-content-center justify-content-lg-between p-4 border-bottom">
        <div class="me-5 d-none d-lg-block">
            <span><fmt:message key="footer.get-connected"/></span>
        </div>
        <div>
            <a href="" class="me-4 text-reset">
                <i class="fab fa-facebook-f"></i>
            </a>
            <a href="" class="me-4 text-reset">
                <i class="fab fa-twitter"></i>
            </a>
            <a href="" class="me-4 text-reset">
                <i class="fab fa-google"></i>
            </a>
            <a href="" class="me-4 text-reset">
                <i class="fab fa-instagram"></i>
            </a>
            <a href="" class="me-4 text-reset">
                <i class="fab fa-linkedin"></i>
            </a>
            <a href="https://github.com/Redflame39/bartender-assistant" class="me-4 text-reset">
                <i class="fab fa-github"></i>
            </a>
        </div>
    </section>
    <section class="">
        <div class="container text-center text-md-start mt-5">
            <div class="row mt-3">
                <div class="col-md-3 col-lg-4 col-xl-3 mx-auto mb-4">
                    <h6 class="text-uppercase fw-bold mb-4">
                        Bartender Assistant
                    </h6>
                    <p>
                        <fmt:message key="footer.additional-info"/>
                    </p>
                </div>
                <div class="col-md-2 col-lg-2 col-xl-2 mx-auto mb-4">
                    <h6 class="text-uppercase fw-bold mb-4">
                            <fmt:message key="footer.languages"/>
                    </h6>
                    <p>
                        <a href="${requestScope["jakarta.servlet.forward.request_uri"]}?command=home&locale=en_US"
                           class="text-reset"><fmt:message key="footer.language.en"/>
                        </a>
                    </p>
                    <p>
                        <a href="${requestScope["jakarta.servlet.forward.request_uri"]}?command=home&locale=ru_RU"
                           class="text-reset"><fmt:message key="footer.language.ru"/>
                        </a>
                    </p>
                </div>
                <div class="col-md-3 col-lg-2 col-xl-2 mx-auto mb-4">
                    <h6 class="text-uppercase fw-bold mb-4">
                        <fmt:message key="footer.useful-links"/>
                    </h6>
                    <p>
                        <a href="#" class="text-reset"><fmt:message key="footer.help"/></a>
                    </p>
                </div>
                <div class="col-md-4 col-lg-3 col-xl-3 mx-auto mb-md-0 mb-4">
                    <h6 class="text-uppercase fw-bold mb-4"><fmt:message key="footer.contacts"/></h6>
                    <p><i class="fas fa-home me-3"></i><fmt:message key="footer.contacts.home"/></p>
                    <p>
                        <i class="fas fa-envelope me-3"></i>
                        bartender_assistant@gmail.com
                    </p>
                    <p><i class="fas fa-phone me-3"></i> + 01 234 567 88</p>
                    <p><i class="fas fa-print me-3"></i> + 01 234 567 89</p>
                </div>
            </div>
        </div>
    </section>
    <div class="text-center p-4" style="background-color: rgba(0, 0, 0, 0.05);">
        2021 Copyright:
        <a class="text-reset fw-bold" href="controller?command=home">Bartender Assistant</a>
    </div>
</footer>
