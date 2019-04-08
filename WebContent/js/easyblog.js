$(function(){
	
        $("#returnTop").click(function () {
                var speed=200;//滑动的速度
                $('html,body').animate({ scrollTop: '0px' }, speed, function(){
                });
                return false;

        });
        $(window).scroll(function(){
            var $top = $(window).scrollTop();
                if($top == 0)
                    {$(".feiji").css('opacity','0');}
                else
                    {$(".feiji").css('opacity','1');}       
        });
		
		//页面加载时判断用户是否已经登陆，从Cookie中获取用户信息
		
		
		
		
		
		//登陆确认ajax请求 == start
		
		
		
		
		
		//登陆确认ajax请求 == end
		
		
		//注册确认ajax请求 == start
		
		
		
		
		
		//注册确认ajax请求 == end
		
		
		
	
});