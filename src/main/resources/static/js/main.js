window.onload = function() {

    function onClick() {
        document.querySelector('#modal').style.display ='block';
        document.querySelector('#black_bg').style.display ='block';
    }

    function offClick() {
        document.querySelector('#modal').style.display ='none';
        document.querySelector('#black_bg').style.display ='none';
    }

    document.getElementById('open').addEventListener('click', onClick);
    document.getElementById('close').addEventListener('click', offClick);

};

function goSignUp() {
    location.href="/user/goSignUp";
}
