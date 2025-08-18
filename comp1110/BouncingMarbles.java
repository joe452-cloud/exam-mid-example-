import comp1110.lib.*;
import comp1110.lib.Date;
import static comp1110.lib.Functions.*;
import comp1110.universe.*;
import static comp1110.universe.Colour.*;
import static comp1110.universe.Image.*;
import static comp1110.universe.Universe.*;


sealed interface Direction permits North, South, East, West, NorthEast, NorthWest, SouthEast, SouthWest {}
/** A Direction represents one of eight compass directions.
 *It determines how a marble moves each step.
 */
record North() implements Direction {}
record South() implements Direction {}
record East() implements Direction {}
record West() implements Direction {}
record NorthEast() implements Direction {}
record NorthWest() implements Direction {}
record SouthEast() implements Direction {}
record SouthWest() implements Direction {}

record Marble(Colour colour, int x, int y, Direction direction) {}


record WorldState(Marble m1, Marble m2, Marble m3, Marble m4) {}

boolean isNorth(Direction d){
    return switch (d) {
        case North north -> true;
        default -> false;
    };
}
boolean isSouth(Direction d){
    return switch (d) {
        case South south -> true;
        default -> false;
    };
}
boolean isEast(Direction d){
    return switch (d) {
        case East east -> true;
        default -> false;
    };
}
boolean isWest(Direction d){
    return switch (d) {
        case West west -> true;
        default -> false;
    };
}
boolean isNorthEast(Direction d) {
    return switch (d) {
        case NorthEast northEast -> true;
        default -> false;
    };
}
boolean isNorthWest(Direction d){
    return switch (d) {
        case NorthWest northWest -> true;
        default -> false;
    };
}
boolean isSouthEast(Direction d){
    return switch (d) {
        case SouthEast southEast -> true;
        default -> false;
    };
}
boolean isSouthWest(Direction d){
    return switch (d) {
        case SouthWest southWest -> true;
        default -> false;
    };
}

 Direction bounce(Direction d, int x, int y) {
    boolean atTop = y <= 10;
    boolean atBottom = y >= 490;
    boolean atLeft = x <= 10;
    boolean atRight = x >= 290;

    if (atTop && atLeft) {
        if (isNorth(d)) return new South();
        if (isWest(d)) return new East();
        if (isNorthWest(d)) return new SouthEast();
    } else if (atTop && atRight) {
        if (isNorth(d)) return new South();
        if (isEast(d)) return new West();
        if (isNorthEast(d)) return new SouthWest();
    } else if (atBottom && atLeft) {
        if (isSouth(d)) return new North();
        if (isWest(d)) return new East();
        if (isSouthWest(d)) return new NorthEast();
    } else if (atBottom && atRight) {
        if (isSouth(d)) return new North();
        if (isEast(d)) return new West();
        if (isSouthEast(d)) return new NorthWest();
    } else if (atTop) {
        if (isNorth(d)) return new South();
        if (isNorthEast(d)) return new SouthEast();
        if (isNorthWest(d)) return new SouthWest();
    } else if (atBottom) {
        if (isSouth(d)) return new North();
        if (isSouthEast(d)) return new NorthEast();
        if (isSouthWest(d)) return new NorthWest();
    } else if (atLeft) {
        if (isWest(d)) return new East();
        if (isNorthWest(d)) return new NorthEast();
        if (isSouthWest(d)) return new SouthEast();
    } else if (atRight) {
        if (isEast(d)) return new West();
        if (isNorthEast(d)) return new NorthWest();
        if (isSouthEast(d)) return new SouthWest();
    }
    return d; // 没撞墙就不改变方向
}

 Marble moveOneStep(Marble m) {
    int x = m.x();
    int y = m.y();
    Direction d = m.direction();

    int dx = 0;
    int dy = 0;

    if (isNorth(d)) dy = -1;
    if (isSouth(d)) dy = 1;
    if (isEast(d)) dx = 1;
    if (isWest(d)) dx = -1;
    if (isNorthEast(d)) { dx = 1; dy = -1; }
    if (isNorthWest(d)) { dx = -1; dy = -1; }
    if (isSouthEast(d)) { dx = 1; dy = 1; }
    if (isSouthWest(d)) { dx = -1; dy = 1; }

    return new Marble(m.colour(), x + dx, y + dy, d);
}

WorldState step(WorldState state) {
    Marble[] marbles = { state.m1(), state.m2(), state.m3(), state.m4() };
    Marble[] updated = new Marble[4];

    //意思是：让 i 从 0 数到 3（总共循环 4 次），刚好一次处理一个 marble。
    for (int i = 0; i < 4; i++) {
        //从我们前面准备好的数组里取出第 i 个 marble，例如：第一次循环时：i = 0，取的是 marbles[0]（也就是第一个 marble）
        // 第二次循环时：i = 1，取的是第二个 marble，以此类推
        Marble m = marbles[i];
        //用当前 marble 的方向 + 位置，判断是否撞墙 如果撞了，就返回一个“反弹方向” 如果没撞，就返回原方向 结果保存在 newDir 中
        Direction newDir = bounce(m.direction(), m.x(), m.y());
        //用 原来的坐标 和 新的方向 生成一个新的 marble，叫 turned，但它还没动。
        Marble turned = new Marble(m.colour, m.x(), m.y(), newDir);
        //调用 move() 把 turned 沿着 newDir 移动一step
        // 然后把它放进更新后的数组 updated 中，对应原来第 i 个位置
        updated[i] = moveOneStep(turned);
    }
    //这就是新的世界状态里的四个 marble！
    return new WorldState(updated[0], updated[1], updated[2], updated[3]);
}

Image draw(WorldState state){
    //step1: create a white background
    Image background = Rectangle(300, 500,  Colour.WHITE);
    //step2: create circles on 4 different colours
    Image blue = Circle(10, Colour.BLUE);
    Image red = Circle(10, Colour.RED);
    Image green = Circle(10, Colour.GREEN);
    Image black = Circle(10, Colour.BLACK);

    Image withM1 = OverlayXY(background,blue, state.m1().x(),state.m1().y());
    Image withM2 = OverlayXY(withM1, red, state.m2().x(),state.m2().y());
    Image withM3 =OverlayXY(withM2, green,state.m3().x(),state.m3().y());
    Image withM4 =OverlayXY(withM3,black,state.m4().x(),state.m4().y());

    return withM4;
}

WorldState keyEvent(WorldState ws, String key) {
    if (Equals(key, " ")) {
        return new WorldState(
            new Marble(ws.m1().colour(), ws.m1().x(), ws.m1().y(), randomCardinal()),
            new Marble(ws.m2().colour(),ws.m2().x(), ws.m2().y(), randomCardinal()),
            new Marble(ws.m3().colour(),ws.m3().x(), ws.m3().y(), randomCardinal()),
            new Marble(ws.m4().colour(),ws.m4().x(), ws.m4().y(), randomCardinal())
        );
    } else {
        return ws;
    }
}

WorldState mouseEvent(WorldState ws, int x, int y, String kind) {
    if (Equals(kind, "click")) {
        return new WorldState(
            new Marble(ws.m1().colour(),ws.m1().x(), ws.m1().y(), randomOrdinal()),
            new Marble(ws.m2().colour(),ws.m2().x(), ws.m2().y(), randomOrdinal()),
            new Marble(ws.m3().colour(),ws.m3().x(), ws.m3().y(), randomOrdinal()),
            new Marble(ws.m4().colour(),ws.m4().x(), ws.m4().y(), randomOrdinal())
        );
    } else {
        return ws;
        }
}

Direction randomCardinal() {
    ConsList<Direction> mainList = 
        new Cons(new North(),
        new Cons(new South(),
        new Cons(new East(),
        new Cons(new West(), 
        new Nil<>()))));
    int len = Length(mainList);
    int randIndex = (int)(Math.random() * len);
    return Nth( mainList, randIndex);
}

 Direction randomOrdinal() {
    ConsList<Direction> ordinalList = 
        new Cons(new NorthEast(),
        new Cons(new NorthWest(),
        new Cons(new SouthEast(),
        new Cons(new SouthWest(), new Nil<>()))));
    int len = Length(ordinalList);
    int randIndex = (int)(Math.random() * len);
    return Nth(ordinalList, randIndex );
}

Direction randomDirection() {
     ConsList<Direction> allDirs =
        new Cons(new North(),
        new Cons(new South(),
        new Cons(new East(),
        new Cons(new West(),
        new Cons(new NorthEast(),
        new Cons(new NorthWest(),
        new Cons(new SouthEast(),
        new Cons(new SouthWest(), new Nil<>()))))))));
    
    int len = Length(allDirs);
    int randIndex = (int)(Random() * len);
    return Nth(allDirs, randIndex );
}

WorldState getInitialState() {
    Marble m1= new Marble(Colour.BLUE, 75, 125, randomDirection());
    Marble m2= new Marble(Colour.RED, 225, 125, randomDirection());
    Marble m3= new Marble(Colour.GREEN, 75, 375, randomDirection());
    Marble m4 =new Marble(Colour.BLACK,225, 375, randomDirection());

    return new WorldState(m1, m2, m3, m4);
}

int getX(Marble m) {
    return m.x();
}

int getY(Marble m) {
    return m.y();
}

Direction getDirection(Marble m) {
    return m.direction();
}

Marble getMarble1(WorldState ws) {
    return ws.m1();
}

Marble getMarble2(WorldState ws) {
    return ws.m2();
}

Marble getMarble3(WorldState ws) {
    return ws.m3();
}

Marble getMarble4(WorldState ws) {
    return ws.m4();
}

