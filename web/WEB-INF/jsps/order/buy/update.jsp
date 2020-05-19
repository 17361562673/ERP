<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="../../taglib.jsp"%>
<script type="text/javascript">

	//加载两个change方法
	$(function () {

		//加载商品类别
		$("#supplierId").change(function () {
			//获得供应商id
			var suppplierId=$(this).val();
			loadPt(suppplierId);
			//修改供应商让它选择后就不能再被选择(将disabled属性设置成true)
			$(this).attr("disabled",true);
			//给隐藏域的supplierId赋值
			// $("#supplierHidden").val(suppplierId);
		});

		//加载商品
		$(".goodsType").change(function () {
			//同步隐藏域的值
			$(this).next("input").val($(this).val());
			//获得商品类型id
			var productTypeId=$(this).val();
			var trObj=$(this).parent().parent();
			loadProduct(trObj,productTypeId);
		});



		//对goods的判断
		$(".goods").change(function () {
			$(this).next("input").val($(this).val());
			//把自己拿到
			var productId1=$(this).val();
			//将change的this进行保留
			var productThis=$(this);
			//设置标志
			var count=0;
			$(this).parent().parent().parent().find(".goods").each(function () {
				//获得它的productId
				var productId=$(this).val();
				if (productId==productId1) {
					count++;
				}
			});
			//和自己比了一次变成一 如果有重复的就变成2了
			if (count == 2) {
				Dialog.alert("商品已经存在");
				//如果判断了 重复则把当前的option删掉
				$(this).find("[value='" + productId1 + "']").remove();
				return false;
			} else {
				//调用加载商品的方法
			  loadProductDetail(productThis.parent().parent(),productId1);
			}
		});


		//当数量变化的时候，小计和总计都会发生变化
		$(".num").keyup(function () {
            //获得商品数量
			var count=parseInt($(this).val());
			//获得单价
			var price=parseInt($(this).parent().parent().find(".prices").val());

			//设置小计
			$(this).parent().parent().find(".total").html(price*count+"元");
			//给trTotal(小计)属性赋值
			$(this).parent().parent().find(".total").attr("trTotal",price*count);
			//遍历小计
			var totalPrice=0;
			$(this).parent().parent().parent().find(".total").each(function () {
				//$(this).attr("trTotal")得到的是字符串必须进行parseInt的转换
				var trTotal=parseInt($(this).attr("trTotal"));
				totalPrice=totalPrice+trTotal;
			});
			$(this).parent().parent().parent().find(".all").html(totalPrice+"元");
		});


        //当单价变化的时候，小计和总计都会发生变化
		$(".prices").keyup(function () {
			//获得单价
			var price=parseInt($(this).val());
			//获得商品数量
			var count=parseInt($(this).parent().parent().find(".num").val());

			//设置小计
			$(this).parent().parent().find(".total").html(price*count+"元");
			//给trTotal(小计)属性赋值
			$(this).parent().parent().find(".total").attr("trTotal",price*count);
			//遍历小计给总计赋值
			var totalPrice=0;
			$(this).parent().parent().parent().find(".total").each(function () {
				//$(this).attr("trTotal")得到的是字符串必须进行parseInt的转换
				var trTotal=parseInt($(this).attr("trTotal"));
				totalPrice=totalPrice+trTotal;
			});
			$(this).parent().parent().parent().find(".all").html(totalPrice+"元");
		});


		//点击新建新加一行
		$("#add").click(function () {
			var value=$("#supplierId").val();
			//先判断是否已经选择了
			if (value !== "") {
				//clone的时候一定记得不要忘记加true
				var defaultTr=$("#defaultTr").clone(true);
				//把新建出来的行的属性id给删除以区别第一行和其他行
				defaultTr.removeAttr("id");
				defaultTr.find(".goods").empty();
				var productTypeId=defaultTr.find(".goodsType").val();
				$("#finalTr").before(defaultTr);
				loadProduct(defaultTr,productTypeId);
			}
		});


		//删除一行
		$(".deleteBtn").click(function () {
			var trObj=$(this).parent().parent();
			var trId=trObj.attr("id");
			if (trId == "defaultTr") {
				Dialog.alert("默认行不能删除");
			} else {
				trObj.remove();
				//遍历小计
				var totalPrice=0;
				$("#order").find(".total").each(function () {
					//$(this).attr("trTotal")得到的是字符串必须进行parseInt的转换
					var trTotal=parseInt($(this).attr("trTotal"));
					totalPrice=totalPrice+trTotal;
				});
				$("#order").parent().find(".all").html(totalPrice+"元");
			}
		});

	});


	//ajax加载商品类别
	function loadPt(supplierId) {
		var trObj=$("#defaultTr");
		$.ajax({
			url:"${path}/ajax_supplier_getSupplier.action",
			type:"post",
			data:{
				"query.supplierId":supplierId
			},
			async:false,
			dataType:"text",
			success:function (responseText) {
				//清空option
				trObj.find(".goodsType").empty();
				var jsonArray=$.parseJSON(responseText);
				for (var i = 0; i < jsonArray.length; i++) {
					//开始给option追加我们需要的
					trObj.find(".goodsType").append("<option value='"+jsonArray[i].productTypeId+"'>"+jsonArray[i].name+"</option>")
				}
			}
		});
		//获得商品类别
		var ptTypeId=$("#defaultTr").find(".goodsType").val();
		//加载商品
		loadProduct(trObj,ptTypeId);
	}



	//ajax加载类别下的商品
	function loadProduct(trObj,productTypeId) {
		$.ajax({
			url:"${path}/ajax_productType_getProduct.action",
			type:"post",
			data:{
				"query.productTypeId":productTypeId
			},
			async:false,
			dataType:"text",
			success:function (responseText) {
				//清空option
				trObj.find(".goods").empty();
				var jsonArray=$.parseJSON(responseText);
				for (var i = 0; i < jsonArray.length; i++) {
					//默认是不相等的 设置为false
					var isAppend=false;
					//获得table下所有的goods
					$("#order").find(".goods").each(function () {
						//获得它的productId
						var productId=$(this).val();
						if (productId ==jsonArray[i].productId+"") {
							isAppend=true;
						}
					});
					if (!isAppend) {
						//开始给option追加我们需要的
						trObj.find(".goods").append("<option value='"+jsonArray[i].productId+"'>"+jsonArray[i].name+"</option>");
					}
				}
			}
		});
		var productId=$(trObj).find(".goods").val();
		loadProductDetail(trObj,productId);
	}


	//加载商品2
	function loadProductDetail(trObj,productId) {
		$.ajax({
			url:"${path}/ajax_product_getProduct.action",
			type:"post",
			data:{
				"query.productId":productId
			},
			dataType:"text",
			success:function (responseText) {
				var product=$.parseJSON(responseText);
				//在ajax 下$(this)是ajax的this 不是我们change的$(this)
				//设置商品单价
				trObj.find(".prices").val(product.inPrice);
				//获得商品数量
				var count=parseInt(trObj.find(".num").val());
				//设置小计
				trObj.find(".total").html(product.inPrice*count+"元");
				//给trTotal(小计)属性赋值
				trObj.find(".total").attr("trTotal",product.inPrice*count);
				//遍历小计
				var totalPrice=0;
				trObj.parent().find(".total").each(function () {
					//$(this).attr("trTotal")得到的是字符串必须进行parseInt的转换
					var trTotal=parseInt($(this).attr("trTotal"));
					totalPrice=totalPrice+trTotal;
				});
				trObj.parent().find(".all").html(totalPrice+"元");
			}
		});
	}

	//提交表单
	function updateOrder() {
		//设置正确的标志result
		var result="";
		$("#orderInputForm").ajaxSubmit({
			async:false,
			dataType:"text",
			success:function(responseText){
				//如果后台添加emp正确返回success
				result = responseText;
			}
		});
		return result;
	}

</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">订单添加</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${path}/ajax_orderModel_updateOrder.action" method="post" id="orderInputForm">
			<input type="hidden"  name="order.orderId" value="<s:property value="order.orderId"/>">
			<input type="hidden"  name="order.orderChecker.empId" value="<s:property value="order.orderChecker.empId"/>">
			<input type="hidden"  name="order.checkTime" value="<s:property value="order.checkTime"/>">
			<input type="hidden"  name="order.orderNum" value="<s:property value="order.orderNum"/>">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					   style="font-size:14px; font-weight:bold; font-family:"黑体";">
				<tr>
					<td width="68px" height="30">供应商：</td>
					<td width="648px">
						<%--hibernate根据ordermodel和supplier多对一的关系 进行查询的(前台收到order后 hibernate根据对应关系进行查询)--%>
						<s:select id="supplierId" list="#sList" listKey="supplierId" listValue="name" name="order.supplier.supplierId" cssClass="kuan" style="width:300px" headerKey="" headerValue="--请选择--"></s:select>
						<%--<input type="hidden" id="supplierHidden" name="order.supplierId">--%>
					</td>
					<td height="30">
						<a id="add" href="javascript:void(0)"><img src="${path}/images/can_b_02.gif" border="0" /> </a>
					</td>
				</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table id="order" width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td width="25%" height="30">商品类别</td>
						<td width="25%">商品名称</td>
						<td width="10%">采购数量</td>
						<td width="15%">单价</td>
						<td width="15%">合计</td>
						<td width="10%">操作</td>
					</tr>
					<s:iterator value="order.orderDetails" var="detail">
						<tr align="center" bgcolor="#FFFFFF" id="defaultTr">
							<td height="30">
								<s:select list="order.supplier.productTypes" listKey="productTypeId" listValue="name" name="#detail.product.productType.productTypeId" cssClass="goodsType" cssStyle="width: 200px"></s:select>
								<input type="hidden"  name="productTypeId" value="<s:property value="#detail.product.productType.productTypeId"/>">
							</td>
							<td>
								<s:select list="#detail.product.productType.products" listKey="productId" listValue="name" name="#detail.product.productId" cssClass="goods" cssStyle="width: 200px"></s:select>
								<input type="hidden"  name="productId" value="<s:property value="#detail.product.productId"/>">
							</td>
							<td>
								<input  name="detailNum" class="num order_num" style="width:67px;border:1px solid black;text-align:right;padding:2px" type="text" value="<s:property value="#detail.detailNum"/>"/>
							</td>
							<td><input name="detailPrice" class="prices order_num" style="width:93px;border:1px solid black;text-align:right;padding:2px" type="text" value="<s:property value="#detail.detailPrice"/>"/> 元</td>
							<td class="total" align="right" trTotal="<s:property value="#detail.detailNum*#detail.detailPrice"/>"><s:property value="#detail.detailNum*#detail.detailPrice"/>&nbsp;元</td>
							<td>
								<a class="deleteBtn delete xiu" value="4" href="javascript:void(0)"><img src="${path}/images/icon_04.gif" /> 删除</a>
							</td>
						</tr>
					</s:iterator>

					<tr id="finalTr" align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td height="30" colspan="4" align="right">总&nbsp;计:&nbsp;</td>
						<td class="all" width="16%" align="right"><s:property value="order.totalPrice"/>&nbsp;&nbsp;元</td>
						<td>&nbsp;</td>
					</tr>
				</table>
			</div>
		</form>
	</div>

	<div class="content-bbg"></div>
</div>
