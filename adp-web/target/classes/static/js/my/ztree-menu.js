function getMenuTree() {
	var root = {
		id : 0,
		name : "菜单",
		open : true,
	};

	$.ajax({
		type : 'get',
		url : '/listMenuAll',
		contentType : "application/json; charset=utf-8",
		async : false,
		success : function(data) {
			var length = data.length;
			var children = [];
			for (var i = 0; i < length; i++) {
				var d = data[i];
				var node = createNode(d);
				children[i] = node;
			}
			root.children = children;
		}
	});
	return root;
}

function getMenuTrees() {
	var root = {
		id : 0,
		name : "按钮",
		open : true,
	};

	$.ajax({
		type : 'get',
		url : '/listMenuAlls',
		contentType : "application/json; charset=utf-8",
		async : false,
		success : function(data) {
			var length = data.length;
			var children = [];
			for (var i = 0; i < length; i++) {
				var d = data[i];
				var node = createNode(d);
				children[i] = node;
			}

			root.children = children;
		}
	});

	return root;
}



function initMenuDatas(roleId){
	$.ajax({
		type : 'get',
		url : '/permissions?roleId=' + roleId,
		success : function(data) {
			var length = data.length;
			var ids = [];
			for(var i=0; i<length; i++){
				ids.push(data[i]['id']);
			}
			
			initMenuCheck(ids);
		}
	});
}

function initMenuCheck(ids) {
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	var length = ids.length;
	if(length > 0){
		var node = treeObj.getNodeByParam("id", 0, null);
		treeObj.checkNode(node, true, false);
	}
	
	for(var i=0; i<length; i++){
		var node = treeObj.getNodeByParam("id", ids[i], null);
		treeObj.checkNode(node, true, false);
	}
	
}

function getCheckedMenuIds(){
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes = treeObj.getCheckedNodes(true);
	
	var length = nodes.length;
	var ids = [];
	for(var i=0; i<length; i++){
		var n = nodes[i];
		var id = n['id'];
		ids.push(id);
	}

	var treeObjs = $.fn.zTree.getZTreeObj("treeDemos");
	var nodess = treeObjs.getCheckedNodes(true);
	var lengths = nodess.length;
	for(var j=0; j<lengths; j++){
		var n = nodess[j];
		var id = n['id'];
		var pid = n['pId'];
		if(id>0 && pid > 0){
			ids.push(id);
		}
	}
	return ids;
}


function getCheckedDepartmentIds(){
	var treeObj = $.fn.zTree.getZTreeObj("treeDepartment");
	var nodes = treeObj.getCheckedNodes(true);

	var length = nodes.length;
	var ids = [];
	for(var i=0; i<length; i++){
		var n = nodes[i];
		var id = n['id'];
		ids.push(id);
	}
	return ids;
}

function getCheckedPjIds(){
	var treeObj = $.fn.zTree.getZTreeObj("treePj");
	var nodes = treeObj.getCheckedNodes(true);

	var length = nodes.length;
	var ids = [];
	for(var i=0; i<length; i++){
		var n = nodes[i];
		var id = n['id'];
		ids.push(id);
	}
	return ids;
}

function getCheckedCustomerIds(){
	var treeObj = $.fn.zTree.getZTreeObj("treeCustomer");
	var nodes = treeObj.getCheckedNodes(true);

	var length = nodes.length;
	var ids = [];
	for(var i=0; i<length; i++){
		var n = nodes[i];
		var id = n['id'];
		ids.push(id);
	}
	return ids;
}

function createNode(d) {
	var id = d['id'];
	var pId = d['pid'];
	var name = d['name'];
	var child = d['child'];

		var node = {
		open : true,
		id : id,
		name : name,
		pId : pId,
	};

	if (child != null) {
		var length = child.length;
		if (length > 0) {
			var children = [];
			for (var i = 0; i < length; i++) {
				children[i] = createNode(child[i]);
			}

			node.children = children;
		}

	}
	return node;
}

function initParentMenuSelect(){
	$.ajax({
        type : 'get',
        url : '/permissions/parents',
        async : false,
        success : function(data) {
            var select = $("#parentId");
            select.append("<option value='0'>root</option>");
            for(var i=0; i<data.length; i++){
                var d = data[i];
                var id = d['id'];
                var name = d['name'];
                
                select.append("<option value='"+ id +"'>" +name+"</option>");
            }
        }
    });
}

function getSettting() {
	var setting = {
		check : {
			enable : true,
			chkboxType : {
				"Y" : "ps",
				"N" : "ps"
			}
		},
		async : {
			enable : true,
		},
		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "pId",
				rootPId : 0
			}
		},
		callback : {
			onCheck : zTreeOnCheck
		}
	};

	return setting;
}

function zTreeOnCheck(event, treeId, treeNode) {
//	console.log(treeNode.id + ", " + treeNode.name + "," + treeNode.checked
//			+ "," + treeNode.pId);
//	console.log(JSON.stringify(treeNode));
}

/*事业部Tree*/
function getDepartmentTree() {
	var root = {
		id : 0,
		name : "事业部",
		open : true,
	};

	$.ajax({
		type : 'get',
		url : '/listDepartmentAll',
		contentType : "application/json; charset=utf-8",
		async : false,
		success : function(data) {
			var length = data.length;
			var children = [];
			for (var i = 0; i < length; i++) {
				var d = data[i];
				var node = createDepartmentNode(d);
				children[i] = node;
			}

			root.children = children;
		}
	});
	return root;
}



function createDepartmentNode(d) {
	var id = d['serviceDepartmentCode'];
	var pId = 0;
	var name = d['serviceDepartmentName'];

	var node = {
		open : true,
		id : id,
		name : name,
		pId : pId,
	};
	return node;
}


/*项目Tree*/
function getPjTree(txtObj) {
	var root = {
		id : 0,
		name : "请选择",
		open : true,
	};
	/*if(nodeList!=null){
		root.children = nodeList;
	}else{
		$.ajax({
			type : 'get',
			url : '/listPjMap',
			data: nodeList,
			contentType : "application/json; charset=utf-8",
			async : false,
			success : function(data) {
				var length = data.length;
				var children = [];
				for (var i = 0; i < length; i++) {
					var d = data[i];
					var node = createPjNode(d);
					children[i] = node;
				}
				root.children = children;
			}
		});
	}*/
	$.ajax({
		type : 'get',
		url : '/listPjMap?txtObj=' + txtObj,
		contentType : "application/json; charset=utf-8",
		async : false,
		success : function(data) {
			var length = data.length;
			var children = [];
			for (var i = 0; i < length; i++) {
				var d = data[i];
				var node = createPjNode(d);
				children[i] = node;
			}
			root.children = children;
		}
	});
	return root;
}

/*客户Tree*/
function getCustomerTree(nodeList) {
	var root = {
		id : 0,
		name : "请选择",
		open : true,
	};
	if(nodeList!=null){
		root.children = nodeList;
	}else{
		$.ajax({
			type : 'get',
			url : '/listCustomerMap',
			contentType : "application/json; charset=utf-8",
			async : false,
			success : function(data) {
				var length = data.length;
				var children = [];
				for (var i = 0; i < length; i++) {
					var d = data[i];
					var node = createPjNode(d);
					children[i] = node;
				}
				root.children = children;
			}
		});
	}
	return root;
}


function createPjNode(d) {
	var id = d['id'];
	var pId = d['pid'];;
	var name = d['name'];
	var child = d['child'];

	var node = {
		open : true,
		id : id,
		name : name,
		pId : pId,
	};

	if (child != null) {
		var length = child.length;
		if (length > 0) {
			var children = [];
			for (var i = 0; i < length; i++) {
				children[i] = createNode(child[i]);
			}

			node.children = children;
		}

	}
	return node;
}


