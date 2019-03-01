var Engine = Matter.Engine,
    World = Matter.World,
    Constraint = Matter.Constraint;
    MouseConstraint = Matter.MouseConstraint;
    Mouse = Matter.Mouse;
    Bodies = Matter.Bodies;

var engine;
var world;
var ground;
var circs = [];
var circs2 = [];
var numCircs = 11;
var mCont;

function setup() {
  var canvas = createCanvas(500,500);
  engine = Engine.create();
  world = engine.world;
  Engine.run(engine);
  ground = new Wall(width/2,height,width,50,0);

  var x = 50;
  for(var i = 0;i < numCircs;i++) {
    circs.push(new Circle(x,50,10,i));
    circs2.push(new Circle(x,300,10,i));
    x += 40;
  }
  for(i = 0;i < numCircs - 1;i++) {
    var options1 = {
      bodyA: circs[i].body,
      bodyB: circs[i+1].body,
      length: 20,
      stiffness: 0.1
    }
    var options2 = {
      bodyA: circs2[i].body,
      bodyB: circs2[i+1].body,
      length: 10,
      stiffness: 0.3
    }
    var constraint1 = Constraint.create(options1);
    World.add(world,constraint1);
    var constraint2 = Constraint.create(options2);
    World.add(world,constraint2);
  }

  var temp = Mouse.create(canvas.elt);
  temp.pixelRatio = pixelDensity();
  options = {
    mouse: temp
  }
  mCont = MouseConstraint.create(engine,options);
  World.add(world,mCont);
}

function mousePressed() {

}

function mouseDragged() {

}

function draw() {
  background(90);
  strokeWeight(1);
  stroke(255);
  fill(190);
  ground.show();
  for(var i = 0;i < numCircs;i++) {
    circs[i].show();
    circs2[i].show();
  }
}
