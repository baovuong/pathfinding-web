
var World = function(width, height) {
    this.width = width;
    this.height = height;
    this.grid = new Array(height);
    for (var i=0; i<height; i++) {
    	this.grid[0] = new Array(width);
    }
}

World.prototype.getStart() {
	
}

World.prototype.getGoal() {
	
}

World.prototype.getObstacles() {
	
}

World.prototype.createElement = function() {
    var world = document.createElement("table");
    world.className = "world";

    // add cells
    for (var r = 0; r < this.height; ++r) {
        var row = document.createElement("tr");
        for (var c = 0; c < this.width; ++c) {
            var column = document.createElement("td");
            //column.style.width = '1px'; //''+100/this.width+'%';
            column.innerHTML = '&nbsp;';
            column.addEventListener('click', function() {
            	// toggle between obstacle
            });
            row.appendChild(column);
        }
        world.appendChild(row);
    }
    
    return world;    
};
