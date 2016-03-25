

function showContent(link, contentElement) {

    var cont = document.getElementById(contentElement);


    var http = createRequestObject();					// создаем ajax-объект
    if( http ) {
        http.open('get', link);							// инициируем загрузку страницы
        http.onreadystatechange = function () {			// назначаем асинхронный обработчик события
            if(http.readyState == 4) {
                cont.innerHTML = http.responseText;		// присваиваем содержимое
            }
        }
        http.send(null);
    } else {
        document.location = link;	// если ajax-объект не удается создать, просто перенаправляем на адрес
    }
}

// создание ajax объекта
function createRequestObject() {
    try { return new XMLHttpRequest() }
    catch(e) {
        try { return new ActiveXObject('Msxml2.XMLHTTP') }
        catch(e) {
            try { return new ActiveXObject('Microsoft.XMLHTTP') }
            catch(e) { return null; }
        }
    }
}