<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
     <!--Start Breadcrumb-->
<div class="row">
	<div id="breadcrumb" class="col-xs-12">
	<a href="#" class="show-sidebar">
						  <i class="fa fa-bars"></i>
						</a>
		<ol class="breadcrumb pull-left">
			<li><a href="index">首页</a></li>
			<li><a href="#">系统管理</a></li>
			<li><a href="#">权限管理</a></li>
		</ol>
	</div>
</div>       
            <div class="container-fluid">
                       <div class="row">
                    <div class="col-lg-12">
                    <div class="box ui-draggable ui-droppable">
				<div class="box-header">
					<div class="box-name">
						<i class="fa fa-coffee"></i> <span>权限管理</span>
					</div>
					<div class="box-icons">
						<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
						</a> <a class="expand-link"> <i class="fa fa-expand"></i>
						</a> <a class="close-link"> <i class="fa fa-times"></i>
						</a>
					</div>
					<div class="no-move"></div>
				</div>
				<div class="box-content">
				<button id="addpermission" type="button" class="btn btn-primary">添加新权限</button>
					 <table id="grid-data" class="table table-condensed table-hover table-striped">
							        <thead>
							            <tr>
							                <th data-column-id="id" data-identifier="true" data-type="numeric">权限id</th>
							                <th data-column-id="permissionName">权限名</th>
							                <th data-formatter="commands">操作</th>
							            </tr>
							        </thead>
							    </table>
				
                </div>
                            </div>
                        </div>
                    </div>
</div>
<script type="text/javascript">
    $(document).ready(function(){
	    var grid=$("#grid-data").bootgrid({
	        ajaxSettings: {
		        method: "GET"
    		},
	    	navigation:2,
  			columnSelection:false,
		    ajax:true,
		    url:"permissions",
		    formatters: {
		    "commands": function(column, row)
		    {
		            return "<button class=\"btn btn-xs btn-default ajax-link\" onclick=\"deletepermission("+row.pid+")\" data-row-id=\"" + row.pid + "\">删除</button>";
		    }
	    	}
	    
	    }).on("loaded.rs.jquery.bootgrid", function()
	    		{
	    	    	
	    	    });
	    
	  });
	  
	 function deletepermission(pid){
	 	$.ajax({
	     type: 'GET',
	     url: "deletepermission/"+pid,
	     success:function(data) {  
	             alert("删除成功！");  
	             LoadAjaxContent("permissionadmin");
      	 }
		});
	 } 
	 
	  $(document).ready(function(){
	  	$("#addpermission").click(function(){
	  		$("#permissioninfo").modal();
	  		$("#permissionName").val("");
	    	$("#btn").click(function(){
	    		if($("#permissionName").val()=="")
	    		{
	    			alert("权限名不得为空");
	    			return false;
	    		}
	    		$.post("addpermission",$("form").serialize(),function(){
	    			$("#permissioninfo").modal('hide');
	    			LoadAjaxContent("permissionadmin");
	    			history.go(0);
	    		});
	    	});
	    
	  	});
	  	
	  });
	 
    </script>
    <div id="permissioninfo" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">编辑权限信息</h4>
      </div>
      <form class="form-horizontal" method="post">
      <div class="modal-body">
        	<div class="row form-group">
			    <label for="permissionName" class="col-sm-1 control-label">权限名</label>
			    <div class="col-sm-11">
			      <input type="text" name="permissionName" class="form-control" id="permissionName">
			    </div>
        	</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" id="btn" class="btn btn-primary">保存</button>
      </div>
      </form>
    </div>
  </div>
</div>