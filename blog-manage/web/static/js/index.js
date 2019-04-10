$(function() { 
	 var rootPath = getRootPath();
	    function getRootPath() {
	    	var curPath = window.location.href;
	    	var pathName = window.location.pathname;
	    	var rootPath = curPath.substring(0, curPath.indexOf(pathName, 0))
	    			+ pathName.substring(0, pathName.indexOf("/", 1));
	    	return rootPath;
	    }
	    var userUrl = rootPath + "/user/findUser.json";
		$.ajax({
			url : userUrl,
			dataType : 'json',
			type : 'GET',
			success : function(data) {
				console.log(data);
				if (data.state == "success") {
					$("#username").html(data.data.truename);
					$("#loginuser").html(data.data.truename);
					$("#userpicture").attr("src", data.data.picture);
				}
			}
		});
		
		// customized by wukai
		ajaxMenu();
		// customized by wukai
		// 初始化菜单
		function ajaxMenu() {
			var menuUrl = rootPath + "/menu/menus.json";
			$.ajax({
				url : menuUrl,
				type : 'GET',
				dataType : 'json',
				success : function(data) {
					initMenu(data.data);
				    menuTree();
				}
			});
		}
		
		/* 菜单展开和折叠 根页面点击 */
		function menuTree() {
			/**
			 * 菜单折叠或展开事件
			 */
			$(".sidebar-menu a[type=tree]").each(function(){
				 var btn = $(this);
		         var isActive = $(this).parent().hasClass('active');
		         // 注意first和[0]的区别
		         var menu = $(this).siblings(".treeview-menu").first();
		         if (isActive) {
		             menu.show();
		             btn.children(".fa-angle-left").first().removeClass("fa-angle-left").addClass("fa-angle-down");
		         }
		         btn.click(function(e){
			         e.preventDefault();
			         if (isActive) {
			                   // Slide up to close menu
			              menu.slideUp();
			              isActive = false;
			              btn.children(".fa-angle-down").first().removeClass("fa-angle-down").addClass("fa-angle-left");
			              btn.parent("li").removeClass("active");
			         } else {
			                   // Slide down to open menu
			              menu.slideDown();
			              isActive = true;
			              btn.children(".fa-angle-left").first().removeClass("fa-angle-left").addClass("fa-angle-down");
			              btn.parent("li").addClass("active");
			         }
		         });
		         menu.find("li > a").each(function() {
		             var pad = parseInt($(this).css("margin-left")) + 10;
		             $(this).css({"margin-left": pad + "px"});
		         });
			});
			
			/**
			 * 根目录点击事件,添加获取跳转页面
			 */
			$(".sidebar-menu a[type=root]").on("click", function(){
				var url = $(this).attr("url");
				var menuid = $(this).attr("menuid");
				var b = true;
				$(".tab-content div").each(function(){
					if(menuid == $(this).attr("id")) {
						b = false;
						return;
					}
				});
				if(!b) {
					$("#myTab a[href=#" + menuid +"]").tab("show");
					return;
				}
				var tabHtml = "<li ><a href='#"+ menuid +"' data-toggle='tab'>" + $(this).text() +"<i class='ion-close'></i></a></li>";
				$("#myTab").append(tabHtml);
				
				var tabContent = "<div id='" + menuid +"' class='tab-pane fade'><iframe src='" + url +"'></iframe></div>";
				$("#tabContent").append(tabContent);
				
				//==== 关闭tab点击事件
				var closeBtn = $("#myTab a[href=#" + menuid + "] .ion-close")[0];
				closeBtn.onclick = function(){
					$("div").remove("#" + menuid);
					$("a[href=#" + menuid + "]").parent().remove();
				};
				//==== 关闭tab点击事件
				
				$("#myTab a[href=#" + menuid +"]").tab("show");
			});
			
		}
		
		
		var menuHtml = "";
		function initMenu(menus) {
			// var html = "";
			var menu;
			for(var i=0, maxLength = menus.length; i < maxLength; i++) {
				menu = menus[i];
				if(menu.children != null && menu.children.length > 0 ) {
					menuHtml += "<li class='treeview'> <a type='tree' href='#'><i class='fa fa-circle'></i> <span>" + menu.name +"</span><i class='fa fa-angle-left pull-right'></i></a>";
					initChildMenu(menu.children);
				} else {
					menuHtml += "<li class='treeview'><a href='#' type='root' menuid='" + menu.id +"'  url='"+ menu.url +"'><i class='fa fa-circle-o'></i> <span>" + menu.name +"</span></a>";
				}
				menuHtml += "</li>";
			}
		// $("#sysMenu").html(menuHtml);
			document.getElementById("sysMenu").innerHTML = menuHtml;
		}
		
		function initChildMenu(childMenus) {
			var childMenu;
			menuHtml += "<ul class='treeview-menu'>";
			for(var i = 0, maxLength = childMenus.length; i < maxLength; i++) {
				childMenu = childMenus[i];
				if(childMenu.children != null && childMenu.children.length > 0 ) {
					menuHtml += "<li><a href='#' type='tree'><i class='fa fa-circle'></i>" + childMenu.name +"<i class='fa fa-angle-left pull-right'></i></a>";
					initChildMenu(childMenu.children);
				} else {
					menuHtml += "<li><a href='#' type='root' menuid='" + childMenu.id +"' url='" + childMenu.url +"'><i class='fa fa-circle-o'></i>" + childMenu.name +"</a>";
				}
				menuHtml += "</li>";
			}
			menuHtml += "</ul>";
		}
})