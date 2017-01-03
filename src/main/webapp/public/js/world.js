function initWorld(width, height) {
    var world = document.getElementById('world');
    // add cells
    for (var r = 0; r < height; ++r) {
        var row = document.createElement('tr');
        for (var c = 0; c < width; ++c) {
            var column = document.createElement('td');
            // column.style.width = '1px'; //''+100/this.width+'%';
            column.innerHTML = '&nbsp;';
            
            if ((r == 0 && c == 0) || (r == height-1 && c == width-1)) {
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
            for (var i=0; i<result.length; i++) {
                var option = document.createElement('option');
                option.text = result[i];
                select.add(option);
            }
        }
    }
    request.send();
}

function findPath(algorithm) {
    var world = document.getElementById('world');
}