function initWorld(width, height) {
    var world = document.getElementById('world');
    // add cells
    for (var r = 0; r < height; ++r) {
        var row = document.createElement('tr');
        for (var c = 0; c < width; ++c) {
            var column = document.createElement('td');
            // column.style.width = '1px'; //''+100/this.width+'%';
            column.innerHTML = '&nbsp;';

            if ((r == 0 && c == 0) || (r == height - 1 && c == width - 1)) {
                column.className = 'point';
            } else {
                column.onclick = function(e) {
                    var sender = e.srcElement || e.target;
                    if (sender.className == 'obstacle') {
                        sender.className = '';
                    } else {
                        sender.className = 'obstacle';
                    }
                }
            }

            row.appendChild(column);
        }
        world.appendChild(row);
    }
}

function getAlgorithms(select) {
    var request = new XMLHttpRequest();
    request.open('GET', 'api/algorithms', true);
    request.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var result = JSON.parse(this.responseText);
            for (var i = 0; i < result.length; i++) {
                var option = document.createElement('option');
                option.text = result[i];
                select.add(option);
            }
        }
    }
    request.send();
}

function clearPath() {
    var world = document.getElementById('world');
    var pathSteps = world.getElementsByClassName('path');
    for (var i=0; i<pathSteps.length; i++) {
        pathSteps[i].className = '';
    }
}


function findPath(algorithm) {
    var world = document.getElementById('world');
    var rows = world.getElementsByTagName('tr');
    var height = rows.length;
    var width = rows[0].getElementsByTagName('td').length;

    var obstacles = new Array();
    var cells = world.getElementsByTagName('td');
    for (var i = 1; i <= cells.length; i++) {
        if (cells[i - 1].className == 'obstacle') {
            // add it to obstacles
            var y = i / width + 1;
            var x = i % width;
            x = x == 0 ? width : x;
            obstacles.push({
                'y' : y,
                'x' : x
            });
        }
    }
    
    var request = new XMLHttpRequest();
    request.open('POST', 'api/solve/'+algorithm, true);
    request.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    request.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            alert(this.responseText);
            var result = JSON.parse(this.responseText);
            var path = result.path;
            for (var i=0; i<path.length; i++) {
                var cellIndex = (path[i].y-1)*width + path[i].x - 1;
                if (cells[cellIndex].className == '')
                    cells[cellIndex].className = 'path';
            }
        }
    }  
    request.send(JSON.stringify({
        width: width,
        height: height,
        start: {x: 1, y: 1},
        goal: {x: width, y: height},
        obstacles: obstacles
    }));       

}