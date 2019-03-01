var Engine = Matter.Engine,
    World = Matter.World,
    Bodies = Matter.Bodies;

var engine;
var world;
var boxes = [];
var bound = [];
var circs = [];
var ground;
var wLeft;
var wRight;

function setup() {
  createCanvas(500,500);
  engine = Engine.create();
  world = engine.world;
  Engine.run(engine);
  //bound.push(new Wall(width/2,height,width,50,0));
  bound.push(new Wall(0,height/2,20,height,0));
  bound.push(new Wall(width,height/2,20,height,0));
  bound.push(new Wall(width/2 - 60,350,20,300,-PI/3));
  bound.push(new Wall(width/2 + 60,200,20,300,PI/3));
}

function mousePressed() {
  var w = floor(random(5,30));
  var h = floor(random(5,30));
  boxes.push(new Box(mouseX,mouseY,w,h));
}

function mouseDragged() {
  circs.push(new Circle(mouseX,mouseY,floor(random(2,10))));
  //circs.push(new Circle(mouseX,mouseY,2));
  //circs.push(new Circle(mouseX,mouseY,2));
}

function draw() {
  background(90);
  strokeWeight(1);
  stroke(255);
  fill(190);
  rectMode(CENTER);
  for(var i = 0;i < boxes.length;i++) {
    boxes[i].show();
    if(boxes[i].isOffScreen()){
      boxes[i].removeFromWorld();
      boxes.splice(i,1);
      i--;
    }
  }
  for(var i = 0;i < bound.length;i++) {
    bound[i].show();
  }
  for(var i = 0;i < circs.length;i++) {
    circs[i].show();
    if(circs[i].isOffScreen()){
      circs[i].removeFromWorld();
      circs.splice(i,1);
      i--;
    }
  }
}
