function Wall(x,y,w,h,ang) {
  this.x = x;
  this.y = y;
  this.w = w;
  this.h = h;
  this.r = floor(random(0,255.9));
  this.g = floor(random(0,255.9));
  this.b = floor(random(0,255.9));
  var options = {
    isStatic: true,
    angle: ang,
    friction: 0
  }
  this.body = Bodies.rectangle(this.x,this.y,this.w,this.h,options);
  World.add(world,this.body);

  this.show = function() {
    var pos = this.body.position;
    var angle = this.body.angle;

    push();
    translate(pos.x,pos.y);
    rectMode(CENTER);
    fill(this.r,this.g,this.b);
    rotate(this.body.angle);
    rect(0,0,this.w,this.h);
    pop();

  }
}
