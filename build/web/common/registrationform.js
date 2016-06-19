/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function submitcheck(submitbutton){
    document.regist.button.disabled = !submitbutton.checked;
}

function checkParameters(){
    var cflag = false;
    var rflag = false;
    var caution = '以下の必須事項が未入力です\n';
    var formreg = '半角英数文字を入力して下さい\n';
    
    //registrationformの各パラメータ
    var username = document.regist.username.value;
    var password = document.regist.password.value;
    var repassword = document.regist.repassword.value;
    var mail = document.regist.mail.value;
    var answer = document.regist.answer.value;
    var reg = /^[a-zA-Z0-9]+$/;
    var mailreg = /[\w.\-]+@[\w\-]+\.[\w.\-]+/;
    
    
    if(username === ""){
        cflag = true; 
        caution += 'ユーザー名/';
    } else {
        if(!username.match(reg)){
            rflag = true;
            formreg += 'ユーザー名/';
        }
    }
    
    if(password === ""){
        cflag = true;
        caution += 'パスワード/';
    } else {
        if(!password.match(reg)){
            rflag = true;
            formreg += 'パスワード/';
        }
    }
    
    if(mail === ""){
        cflag = true;
        caution += 'メールアドレス/';
    }else {
        if(!mail.match(mailreg)){
            rflag = true;
            formreg += 'メールアドレス/';
        }
    }
    
    if(answer === ""){
        cflag = true;
        caution += '秘密の答え';
    } 
    
    if(password !== repassword){
        cflag = true;
        caution = 'パスワードが異なります. 再入力して下さい.';
    }
    
    if(cflag){
        window.alert(caution);
        return false;
    } else if(rflag){
        window.alert(formreg);
        return false;
    }
                
    return true;
    
}


