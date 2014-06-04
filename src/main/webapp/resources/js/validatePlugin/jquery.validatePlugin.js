//图片文件验证
 jQuery.validator.addMethod("isImgFile", function(value, element) {
	 var picture = /.(jpg|jpeg|bmp|gif|png)$/;
     return (value==''||picture.exec(value.toLowerCase()))?true:false;       
 }, "请上传jpg|jpeg|bmp|gif|png图片格式文件");
 
//自定义检验数字和字母
 jQuery.validator.addMethod("chrnum", function(value, element) {
	 var chrnum = /^([a-zA-Z0-9]+)$/;
	 return this.optional(element) || (chrnum.test(value));      
 }, "只能输入数字和字母(字符A-Z, a-z, 0-9)");