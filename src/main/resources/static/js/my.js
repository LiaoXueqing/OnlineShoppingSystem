function myCart(){
    //从商品列表页跳转到购物车页面
    alert("to myCart");
    window.location.href="/myCart";
}
function addItem(){
    //将商品加入购物车

}
function payments(){
    //传购物车商品至后台，返回收据。
    var cartItems = ['ITEM000001','ITEM000001','ITEM000001','ITEM000001','ITEM000001',
                    'ITEM000003-2','ITEM000005','ITEM000005','ITEM000005'];
    alert(cartItems);
    $.ajax({
        url:'/receipts',
        type:"POST",
        contentType : "application/json" ,
        data : JSON.stringify(cartItems),
//        async:false,
        success:function(data){
            alert("success");
            alert(data);
            document.write(data);
        },
        error:function(){
            alert("error");
        }
    });
}

