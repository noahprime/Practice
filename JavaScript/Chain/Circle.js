function Circle(x,y,r,i) {
  this.x = x;
  this.y = y;
  this.r = r;
  this.i = i;
  this.ar = floor(random(0,70));
  this.g = floor(random(54,185));
  this.b = floor(random(130,255.9));
  this.fixed = false;
  if(this.i == (numCircs - 1) || this.i == 0) {
    this.fixed = true;
  }
  var options = {
    friction: 0,
    restitution: 0.1,
    isStatic: this.fixed
  }
  this.body = Bodies.circle(this.x,this.y,this.r,options);
  World.add(world,this.body);

  this.show = function() {
    var pos = this.body.position;
    var angle = this.body.angle;

    push();
    translate(pos.x,pos.y);
    rotate(angle);
    fill(this.ar,this.g,this.b);
    ellipse(0,0,r*2,r*2);
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
