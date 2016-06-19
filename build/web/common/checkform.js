/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function Alert(error){
                var text = '';
                var flag = false;
                switch(error){
                    case 'input':
                    text = 'フォーム入力エラーです.\n半角英数文字が入力されていることを確認してください.';
                    flag = true;
                    break;
                    
                    case 'notexisting':
                    text = 'ログイン出来ません.\nユーザー名またはパスワードを確認してください.';
                    flag = true;
                    break;
                    
                    case 'youwillbebot':
                    text = 'ログイン出来ません.\nボットである可能性があります.';
                    flag = true;
                    break;
                    
                    case 'contact2admin':
                    text = 'エラーが発生しました.\n大変申し訳御座いませんが,後ほど再アクセスしてください.';
                    flag = true;
                    break;
                }
                if(flag){
                window.alert(text);
                }
            }

