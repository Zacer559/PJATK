var imgA;
var imgB;
var markedVectors = [[]]
var ind = 0;

function setup() {
    createCanvas(512, 512);
    background(255);
    imgA = createImage(512, 512);
    imgB = createImage(512, 512);
    imgA.loadPixels();
    imgB.loadPixels();
    var d = pixelDensity();
    for (var i = 0; i < 1024 * 512 * 8 * d; i += 4) { // set it appropriately
        imgA.pixels[i] = 240;
        imgA.pixels[i + 1] = 250;
        imgA.pixels[i + 2] = 240;
        imgA.pixels[i + 3] = 255;
        imgB.pixels[i] = 240;
        imgB.pixels[i + 1] = 240;
        imgB.pixels[i + 2] = 250;
        imgB.pixels[i + 3] = 255;
    }
    imgA.updatePixels();
    imgB.updatePixels();
}

function draw() {
    if (!keyIsDown(32)) {
        image(imgA, 0, 0);
        text('Image A', 10, 20);
    } else {
        image(imgB, 0, 0);
        text('Image B', 10, 20);
    }
}


function makeVector(x, y) {
    var tab = [x, y, 1];
    return tab;
}

function drawVector(img, vec) {
    console.log("Draw " + vec[1]);
    img.set(vec[0], vec[1], 0);
    img.updatePixels();
}


function mouseDragged() {
    var vector = makeVector(mouseX, mouseY);
    drawVector(imgA, vector);
    markedVectors[ind] = vector;
    ind++;

}

function makeIdentity() {
    let identity = [[1, 0, 0],
        [0, 1, 0],
        [0, 0, 1]];
    return identity;
}

function makeTranslation(tx, ty) {
    let translation = [[1, 0, tx],
        [0, 1, ty],
        [0, 0, 1]];
    return translation;
}

function makeScale(sx, sy) {
    let scale = [[sx, 0, 0],
        [0, sy, 0],
        [0, 0, 1]];
    return scale;
}

function makeRotation(angle) {
    let radians = angle / 180 * Math.PI;
    let rotation = [[cos(radians), -sin(radians), 0],
        [sin(radians), cos(radians), 0],
        [0, 0, 1]];
    return rotation;
}

function makeShearTransform(shx, shy) {
    let translation = [[1, shx, 0],
        [shy, 1, 0],
        [0, 0, 1]];
    return translation;
}

function multiplyVectors(m, v) {

    let transformed;

    if (v !== undefined && v[0].length === undefined) {
        transformed = Array(1).fill().map(() => Array(m[0].length));
    } else
        transformed = Array(v.length).fill().map(() => Array(m[0].length));
    console.log(m);
    console.log(v);
    console.log(transformed.length);
    console.log(transformed[0].length);

    var vLen;
    if (v !== undefined && v[0].length === undefined)
        vLen = 1;
    else
        vLen = v.length;


    for (let i = 0; i < vLen; i++) {
        for (let j = 0; j < m[0].length; j++) {
            transformed[i][j] = 0;
            for (let k = 0; k < m.length; k++) {
                if (v[0].length === undefined)
                    transformed[i][j] += m[k][j] * v[k];
                else
                    transformed[i][j] += m[k][j] * v[i][k];
            }
        }
    }
    console.log("transformed vector" + transformed);

    var finMatrix = [];
    console.log(transformed.length);
    console.log(transformed[0].length);
    for (let i = 0; i < transformed.length; i++) {
        for (let j = 0; j < transformed[0].length; j++) {
            transformed[i][j] = Math.floor(transformed[i][j]);
        }
    }

    console.log("original vector " + v);
    console.log("transformed vector" + transformed);
    return transformed;
}

function transform(transformation, tx, ty, sx, sy, angle, shx, shy) {
    console.log("transform");
    if (transformation === "translation") {
        for (let i = 0; i < markedVectors.length; i++) {
            var transformation = makeTranslation(tx, ty);
            var transformedVector = multiplyVectors(transformation, markedVectors[i]);
            document.getElementById("transformMatrix").value = transformation;
            drawVector(imgB, transformedVector[0]);
        }
    } else if (transformation === "scale") {
        for (let i = 0; i < markedVectors.length; i++) {
            var transformation = makeScale(sx, sy)
            var transformedVector = multiplyVectors(transformation, markedVectors[i]);
            document.getElementById("transformMatrix").value = transformation;
            drawVector(imgB, transformedVector[0]);
        }
    } else if (transformation === "rotate") {
        for (let i = 0; i < markedVectors.length; i++) {
            var transformation = makeRotation(angle);
            var transformedVector = multiplyVectors(transformation, markedVectors[i]);
            document.getElementById("transformMatrix").value = transformation;
            drawVector(imgB, transformedVector[0]);
        }
    } else if (transformation === "shear") {
        for (let i = 0; i < markedVectors.length; i++) {
            var transformation = makeShearTransform(shx, shy);
            var transformedVector = multiplyVectors(transformation, markedVectors[i]);
            document.getElementById("transformMatrix").value = transformation;
            drawVector(imgB, transformedVector[0]);
        }
    } else if (transformation === "makeIdentity") {
        for (let i = 0; i < markedVectors.length; i++) {
            var transformation = makeIdentity();
            var transformedVector = multiplyVectors(transformation, markedVectors[i]);
            document.getElementById("transformMatrix").value = transformation;
            drawVector(imgB, transformedVector[0]);
        }
    } else {
        var transformations = makeIdentity();
        transformations = multiplyVectors(makeTranslation(Number(tx), Number(ty)), transformations);
        transformations = multiplyVectors(makeScale(sx, sy), transformations);
        transformations = multiplyVectors(makeRotation(angle), transformations);
        transformations = multiplyVectors(makeShearTransform(shx, shy), transformations);
        document.getElementById("transformMatrix").value = transformations;
        for (let i = 0; i < markedVectors.length; i++) {
            var transformedVector = multiplyVectors(transformations, markedVectors[i]);
            console.log("Transformacja " + transformedVector);
            drawVector(imgB, transformedVector[0]);
        }
    }
}
