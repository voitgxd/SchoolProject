jQuery(document).ready(function() {
	"use strict";
	// Tooltip
		jQuery('.tooltips').tooltip( {
			container : 'body'
		});
		// Popover
		jQuery('.popovers').popover();

		// Show panel buttons when hovering panel heading
		jQuery('.panel-heading').hover(function() {
			jQuery(this).find('.panel-btns').fadeIn('fast');
		}, function() {
			jQuery(this).find('.panel-btns').fadeOut('fast');
		});

		// Close Panel
		jQuery('.panel .panel-close').click(function() {
			jQuery(this).closest('.panel').fadeOut(200);
			return false;
		});

		// Minimize Panel
		jQuery('.panel .panel-minimize')
				.click(
						function() {
							var t = jQuery(this);
							var p = t.closest('.panel');
							if (!jQuery(this).hasClass('maximize')) {
								p.find('.panel-body, .panel-footer').slideUp(
										200);
								t.addClass('maximize');
								t.find('i').removeClass('fa-minus').addClass(
										'fa-plus');
								jQuery(this).attr('data-original-title',
										'Maximize Panel').tooltip();
							} else {
								p.find('.panel-body, .panel-footer').slideDown(
										200);
								t.removeClass('maximize');
								t.find('i').removeClass('fa-plus').addClass(
										'fa-minus');
								jQuery(this).attr('data-original-title',
										'Minimize Panel').tooltip();
							}
							return false;
						});

		// 父菜单点击事件
		jQuery('.leftpanel .nav .parent >a').click(function() {
			var coll = jQuery(this).parents('.collapsed').length;
			if (!coll) {
				jQuery('.leftpanel .nav .parent-focus').each(function() {
					jQuery(this).find('.children').slideUp('fast');
					jQuery(this).removeClass('parent-focus');
				});
				var child = jQuery(this).parent().find('.children');
				if (!child.is(':visible')) {
					child.slideDown('fast');
					if (!child.parent().hasClass('active'))
						child.parent().addClass('parent-focus');
				} else {
					child.slideUp('fast');
					child.parent().removeClass('parent-focus');
				}
			}
			return false;
		});

		// 子菜单点击事件
		jQuery('.leftpanel .nav .parent .children >li').click(function() {
			var coll = jQuery(this).parents('.collapsed').length;
			jQuery('.leftpanel .nav .active').each(function(){
				jQuery(this).removeClass('active');
			});
			var parent = jQuery(this).parent().parent();
			jQuery(parent).removeClass('parent-focus');
			jQuery(parent).addClass('active');
			jQuery(this).addClass('active');
		});

		// Menu Toggle
		jQuery('.menu-collapse').click(function() {
			if (!$('body').hasClass('hidden-left')) {
				if ($('.headerwrapper').hasClass('collapsed')) {
					$('.headerwrapper, .mainwrapper').removeClass('collapsed');
				} else {
					$('.headerwrapper, .mainwrapper').addClass('collapsed');
					$('.children').hide(); // hide sub-menu if leave open
			}
		} else {
			if (!$('body').hasClass('show-left')) {
				$('body').addClass('show-left');
			} else {
				$('body').removeClass('show-left');
			}
		}
		return false;
	}	);

		// Add class nav-hover to mene. Useful for viewing sub-menu
		jQuery('.leftpanel .nav li').hover(function() {
			$(this).addClass('nav-hover');
		}, function() {
			$(this).removeClass('nav-hover');
		});

		// For Media Queries
		jQuery(window).resize(function() {
			hideMenu();
		});

		hideMenu(); // for loading/refreshing the page
		function hideMenu() {

			if ($('.header-right').css('position') == 'relative') {
				$('body').addClass('hidden-left');
				$('.headerwrapper, .mainwrapper').removeClass('collapsed');
			} else {
				$('body').removeClass('hidden-left');
			}

			// Seach form move to left
			if ($(window).width() <= 360) {
				if ($('.leftpanel .form-search').length == 0) {
					$('.form-search').insertAfter($('.profile-left'));
				}
			} else {
				if ($('.header-right .form-search').length == 0) {
					$('.form-search')
							.insertBefore($('.btn-group-notification'));
				}
			}
		}

		collapsedMenu(); // for loading/refreshing the page
		function collapsedMenu() {

			if ($('.logo').css('position') == 'relative') {
				$('.headerwrapper, .mainwrapper').addClass('collapsed');
			} else {
				$('.headerwrapper, .mainwrapper').removeClass('collapsed');
			}
		}

		//指定iframe高度
		$("#mainFrame").load(function(){
		var height=$("#mainFrame").contents().height();
		console.info("height="+height);
		$("iframe#mainFrame").attr("height",height);
		});

		// 加粗当前点击的菜单，更新面包屑导航
		$("[id^=menuSub]").click(function(){
		$("#breadcrumb").show("normal");
		$("[id^=menuSub]").css({"font-weight":"normal"});
		$(this).css({"font-weight":"bold"});
		//更新面包屑导航
		var htmlStr = "<ol class=\"breadcrumb\" style=\"padding-top:31px;\"><li>"+$("#productName").val()+"</li>";
		htmlStr+="<li>"+$(this).parent().parent().parent().find("[id^=menuMain]").find("span").text()+"</li>";
		htmlStr+="<li>"+$(this).text().substr(2,$(this).text().length)+"</li>";
		$("#breadcrumbsub").html(htmlStr+"</ol>");
	    });
		
	
	});

/*//菜单上面显示模块名称(模块名称(如官网后台))
$("#product").delegate("a","click",function(){
	$(".leftpanel-title").html($(this).find("strong").text());
	$("#breadcrumbsub").html($(this).find("strong").text());
});*/
	
	
$(function(){
	$.ajax({
		url:"./home/getProduct",
		type:"GET",
		contentType:"application/json",
		dataType:"json",
		success:function(data){
			$("#product").empty();
			var resultHtml="";
			resultHtml+="<ul class=\"nav navbar-nav\" style=\"padding-top:8px;\"> ";
			$.each(data,function(i,itm){
				resultHtml+=" <li><a href=\"./home?sysFlag="+data[i].sysFlag+"\"><strong style=\"color:white\">"+data[i].resName+"</strong></a></li> "
			});
			resultHtml+=" </ul>";
			$("#product").html(resultHtml);
			//菜单上面显示模块名称(模块名称(如官网后台))
			//第一次登陆时默认显示8864官网
			var productName = ""==$("#productName").val()?$("#product a strong").html():$("#productName").val();
			$(".leftpanel-title").html("<strong>"+productName+"</strong>" );
			$("#breadcrumbsub").html("<ol class=\"breadcrumb\" style=\"padding-top:31px;\"><li>"+productName+"</li></ol>");
		}
	});
});	


