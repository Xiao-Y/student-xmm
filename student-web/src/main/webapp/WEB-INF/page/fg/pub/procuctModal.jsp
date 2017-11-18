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
                    <a href="shop.html">
                        <img id="commodityImag" src="/upload/default.jpg" alt="商品图片"/>
                    </a>
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

        //模板中添加到购物车
        $("#addCartModal").on('click', function () {
            var commodityId = $("#commodityId").val();
            var commodityNum = $("#commodityNum").val();
            addCartModal(commodityId, commodityNum);
        });
    });

    /**
     * 模板中添加到购物车
     * @param commodityId 商品id
     * @param commodityNum 商品数量
     */
    function addCartModal(commodityId, commodityNum) {
        $("#productModal").modal('hide');
        var url = path + '/shoppingCart/addShoppingCart/' + commodityId + "?commodityNum=" + commodityNum;
        $.ajax({
            url: url,
            type: 'POST',
            dataType: 'json',
            success: function (data) {
                new TipBox({type: data.type, str: data.message, hasBtn: true, setTime: 1500});
                $("#myShoppingCart").html("<b>我的购物车</b>(" + data.total + ")");
            },
            error: function () {
                new TipBox({type: 'error', str: '系统错误,请稍后!', hasBtn: true});
            }
        });
    }

    /**
     * 商品列表加入购物车
     * @param commodityId 商品id
     */
    function addCart(commodityId) {
        if (typeof(commodityId) == 'undefined' || commodityId == '') {
            new TipBox({type: 'error', str: '商品不存在或已经下架!', hasBtn: true});
        }
        var url = path + '/shoppingCart/addShoppingCart/' + commodityId;
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

    /**
     * 查看商品详细信息
     * @param commodityId 商品id
     */
    function getProcutModalByCommodityId(commodityId) {
        if (typeof(commodityId) == 'undefined' || commodityId == '') {
            new TipBox({type: 'error', str: '商品不存在或已经下架!', hasBtn: true});
        }
        var url = path + '/fg/fgHome/procuctModal?id=' + commodityId;
        $.ajax({
            type: "POST",
            dataType: "json",
            url: url,
            success: function (commodity) {
                if (commodity.id == null || commodity.id == "null" || commodity.id == "") {
                    new TipBox({type: 'error', str: '商品不存在或已经下架!', hasBtn: true});
                } else {
                    if (typeof commodity.img != "undefindc" && commodity.img != null && commodity.img != '') {
                        $("#commodityImag").attr("src", "/upload/" + commodity.img);
                    } else {
                        $("#commodityImag").attr("src", "/upload/default.jpg");
                    }
                    $("#commodityName").html("<a href='#'>" + commodity.commodityName + "</a>");
                    $("#unitPrice").html('¥' + commodity.unitPrice + '/' + commodity.spec);
                    $("#commodityInfo").html("商品信息：" + commodity.commodityInfo);
                    $("#packing").html("包装：" + commodity.packing);
                    $("#quantity").html("已售出：" + commodity.quantity);
                    $("#commodityId").val(commodity.id);
                    $("#productModal").modal('show');
                }
            },
            error: function (obj) {
                new TipBox({type: 'error', str: '网络错误', hasBtn: true})
            }
        });
    }
</script>
