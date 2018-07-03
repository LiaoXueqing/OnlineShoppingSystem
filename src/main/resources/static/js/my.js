var storage=window.localStorage;
var cartItems = [];
var cartItemsName = [];
function myCart(){
    //从商品列表页跳转到购物车页面
    window.location.href="/myCart";
}
function showShop(){
    var cartItems=JSON.parse(storage.getItem("cartItems"));
    var itemsName=JSON.parse(storage.getItem("cartItemsName"));
    var str = '<table class="table table-bordered"><tr><td>条码</td><td>名称</td></tr>';
    for(var i=0;i<itemsName.length;i++){
        str+='<tr><td>'+cartItems[i]+'</td><td>'+itemsName[i]+'</td></tr>';
    }
    str+='</table>';
    $('#myShop').html(str);
}
function addItem(barcode,name){
    //将商品加入购物车
    cartItems.push(barcode);
    cartItemsName.push(name);
    storage.setItem("cartItems",JSON.stringify(cartItems));
    storage.setItem("cartItemsName",JSON.stringify(cartItemsName));
    console.log("cartItems:"+cartItems+"   cartItemsName:"+cartItemsName);
}
function payments(){
    //传购物车商品至后台，返回收据。
    var cartItems=JSON.parse(storage.getItem("cartItems"));
    $.ajax({
        url:'/receipts',
        type:"POST",
        contentType : "application/json" ,
        data : JSON.stringify(cartItems),
        success:function(data){
            document.write(data);
        },
        error:function(){
            alert("error");
        }
    });
}

