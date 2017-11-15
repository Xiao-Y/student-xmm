<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="productModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button id="buttonClose" type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">x</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="modal-img">
                    <a href="shop.html"><img id="commodityImag" src="/static/fg_js_css/img/product/1.jpg" alt=""/></a>
                </div>
                <div class="modal-pro-content">
                    <h3 id="commodityName"><a href="#">商品名称</a></h3>
                    <div class="pro-rating">
                        <i class="fa fa-star"></i>
                        <i class="fa fa-star"></i>
                        <i class="fa fa-star"></i>
                        <i class="fa fa-star"></i>
                        <i class="fa fa-star-o"></i>
                    </div>
                    <%--<span>(2 customer reviews)</span>--%>
                    <div class="price">
                        <span id="unitPrice">单价</span>
                        <%--<span class="old">$80.11</span>--%>
                    </div>
                    <p id="commodityInfo">商品信息</p>
                    <input id="commodityId" name="commodityId" type="hidden"/>
                    <input id="commodityNum" name="commodityNum" type="number" value="1"/>
                    <button id="addCartModal">加入购物车</button>
                    <div class="product_meta">
                        <span id="quantity" class="tagged_as">已售出</span>
                        <br/>
                        <span id="packing" class="posted_in">包装</span>
                    </div>
                    <%--
                    <div class="social">
                        <a href="#"><i class="fa fa-facebook"></i></a>
                        <a href="#"><i class="fa fa-twitter"></i></a>
                        <a href="#"><i class="fa fa-google-plus"></i></a>
                        <a href="#"><i class="fa fa-instagram"></i></a>
                        <a href="#"><i class="fa fa-pinterest"></i></a>
                    </div>--%>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $(function () {

        $("[name='modelView']").on('click', function () {
            var commodityId = $(this).parent(".commodityOption").find("input[name='commodityId']").val();
            getProcutModalByCommodityId(commodityId);
        });

        $("[name='addCart']").on('click', function () {
            var commodityId = $(this).parent(".commodityOption").find("input[name='commodityId']").val();
            addCart(commodityId);
        });
        $("#addCartModal").on('click', function () {
            var url = path + '/shoppingCart/addShoppingCart/' + $("#commodityId").val() + "?commodityNum=" + $("#commodityNum").val();
            $.ajax({
                url: url,
                type: 'POST',
                dataType: 'json',
                success: function (data) {
                    $("#buttonClose").click();
                    new TipBox({type: data.type, str: data.message, hasBtn: true, setTime: 1500});
                    $("#myShoppingCart").html("<b>我的购物车</b>(" + data.total + ")");
                },
                error: function () {
                    $("#buttonClose").click();
                    new TipBox({type: 'error', str: '系统错误,请稍后!', hasBtn: true});
                }
            });
        });
    });

    //商品加入购物车
    function addCart(id) {
        var url = path + '/shoppingCart/addShoppingCart/' + id;
        var tipBox = null;
        $.ajax({
            url: url,
            type: 'POST',
            dataType: 'json',
            beforeSend: function (XHR) {
                tipBox = new TipBox({type: 'load', str: "正在加入购物车..."});
            },
            success: function (data) {
                tipBox.close();
                new TipBox({type: data.type, str: data.message, hasBtn: true, setTime: 1500});
                $("#myShoppingCart").html("<b>我的购物车</b>(" + data.total + ")");
            },
            error: function () {
                new TipBox({type: 'error', str: '系统错误,请稍后!', hasBtn: true});
            }
        });
    }

    //查看商品详细信息
    function getProcutModalByCommodityId(commodityId) {
        var url = path + '/commodity/procuctModal?id=' + commodityId;
        $.ajax({
            type: "POST",
            dataType: "json",
            url: url,
            success: function (commodity) {
                $("#commodityImag").attr("src", "/static/fg_js_css/img/product/12.jpg");
                $("#commodityName").html("<a href='#'>" + commodity.commodityName + "</a>");
                $("#unitPrice").html('¥' + commodity.unitPrice + '/' + commodity.spec);
                $("#commodityInfo").html("商品信息：" + commodity.commodityInfo);
                $("#packing").html("包装：" + commodity.packing);
                $("#quantity").html("已售出：" + commodity.quantity);
                $("#commodityId").val(commodity.id);
            },
            error: function (obj) {
                $("#buttonClose").click();
                new TipBox({type: 'error', str: '网络错误', hasBtn: true})
            }
        });
    }
</script>
