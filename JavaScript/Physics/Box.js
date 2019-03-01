function Box(x,y,w,h) {
  this.x = x;
  this.y = y;
  this.w = w;
  this.h = h;
  this.r = floor(random(0,255.9));
  this.g = floor(random(0,255.9));
  this.b = floor(random(0,255.9));
  var options = {
    friction: 0,
    restitution: 0.8
  }
  this.body = Bodies.rectangle(this.x,this.y,this.w,this.h,options);
  World.add(world,this.body);

  this.show = function() {
    var pos = this.body.position;
    var angle = this.body.angle;

    push();
    translate(pos.x,pos.y);
    rectMode(CENTER);
    rotate(angle);
    fill(this.r,this.g,this.b);
    rect(0,0,this.w,this.h);
    pop();

  }

  this.isOffScreen = function() {
    var ex = this.body.position.x ;
    var why = this.body.position.y;
    return (ex < -50 || ex > width + 50 || why < -50 || why > height + 50);
  }

  this.removeFromWorld = function() {
    World.remove(world,this.body);
  }
}
