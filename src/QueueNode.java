class QueueNode
{
    Point pt; // The cordinates of a cell
    int dist; // cell's distance of from the source
    QueueNode prev;
    public QueueNode(Point pt, int dist, QueueNode prev)
    {
        this.pt = pt;
        this.dist = dist;
        this.prev = prev;
    }
}