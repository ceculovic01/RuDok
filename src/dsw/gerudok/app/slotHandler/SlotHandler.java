package dsw.gerudok.app.slotHandler;

import dsw.gerudok.app.repository.elements.Slot;
import dsw.gerudok.app.repository.elements.TriangleSlot;

public class SlotHandler implements ISlot{

    public SlotHandler() {

    }

    @Override
    public void changeSlot(Slot slot, String transform, int newPositionX, int newPositionY) {
        if(slot == null){
            return;
        }

        if(transform == "moveTransform"){
            double maxX = 0;
            double maxY = 0;
            double minX = 0;
            double minY = 0;
            if(slot instanceof TriangleSlot){
                double Mx = slot.getPositionX();
                double My = slot.getPositionY();
                double Dx = slot.getPositionX() + (slot.getPositionX() - (slot.getWidth()/2) - slot.getPositionX()) * Math.cos(slot.getAngle()) -
                        (slot.getPositionY() + slot.getHeight() - slot.getPositionY()) * Math.sin(slot.getAngle());
                double Dy = slot.getPositionY() + (slot.getPositionX() - (slot.getWidth()/2) - slot.getPositionX()) * Math.sin(slot.getAngle()) +
                        (slot.getPositionY() + slot.getHeight() - slot.getPositionY()) * Math.cos(slot.getAngle());

                double Cx = slot.getPositionX() + (slot.getPositionX() + (slot.getWidth()/2) - slot.getPositionX()) * Math.cos(slot.getAngle()) -
                        (slot.getPositionY() + slot.getHeight() - slot.getPositionY()) * Math.sin(slot.getAngle());
                double Cy = slot.getPositionY() + (slot.getPositionX() + (slot.getWidth()/2) - slot.getPositionX()) * Math.sin(slot.getAngle()) +
                        (slot.getPositionY() + slot.getHeight() - slot.getPositionY()) * Math.cos(slot.getAngle());
                maxX = Math.max(Mx, Math.max(Cx, Dx));
                minX = Math.min(Mx, Math.min(Cx, Dx));
                maxY = Math.max(My, Math.max(Cy, Dy));
                minY = Math.min(My, Math.min(Cy, Dy));
            }else {
                double Bx = slot.getPositionX() + (slot.getPositionX() + slot.getWidth() - slot.getPositionX()) * Math.cos(slot.getAngle()) -
                        (slot.getPositionY() - slot.getPositionY()) * Math.sin(slot.getAngle());
                double By = slot.getPositionY() + (slot.getPositionX() + slot.getWidth() - slot.getPositionX()) * Math.sin(slot.getAngle()) +
                        (slot.getPositionY() - slot.getPositionY()) * Math.cos(slot.getAngle());

                double Dx = slot.getPositionX() + (slot.getPositionX() - slot.getPositionX()) * Math.cos(slot.getAngle()) -
                        (slot.getPositionY() + slot.getHeight() - slot.getPositionY()) * Math.sin(slot.getAngle());
                double Dy = slot.getPositionY() + (slot.getPositionX() - slot.getPositionX()) * Math.sin(slot.getAngle()) +
                        (slot.getPositionY() + slot.getHeight() - slot.getPositionY()) * Math.cos(slot.getAngle());

                double Cx = slot.getPositionX() + (slot.getPositionX() + slot.getWidth() - slot.getPositionX()) * Math.cos(slot.getAngle()) -
                        (slot.getPositionY() + slot.getHeight() - slot.getPositionY()) * Math.sin(slot.getAngle());
                double Cy = slot.getPositionY() + (slot.getPositionX() + slot.getWidth() - slot.getPositionX()) * Math.sin(slot.getAngle()) +
                        (slot.getPositionY() + slot.getHeight() - slot.getPositionY()) * Math.cos(slot.getAngle());

                maxX = Math.round(Math.max(Math.max(slot.getPositionX(), Bx), Math.max(Cx, Dx)));
                minX = Math.round(Math.min(Math.min(slot.getPositionX(), Bx), Math.min(Cx, Dx)));
                maxY = Math.max(Math.max(slot.getPositionY(), By), Math.max(Cy, Dy));
                minY = Math.min(Math.min(slot.getPositionY(), By), Math.min(Cy, Dy));

            }

            if(maxX > 495){
                if(maxX == slot.getPositionX()){
                    newPositionX = 495;
                }else {
                    newPositionX = (int) (495 - (maxX-slot.getPositionX()));
                }
            }
            if(minX < 5){
                if(minX == slot.getPositionX()){
                    newPositionX = 5;
                }else {
                    newPositionX = (int) (5 + (slot.getPositionX() - minX));
                }
            }
            if(maxY > 495){
                if(maxY == slot.getPositionY()){
                    newPositionY = 495;
                }else {
                    newPositionY = (int) (495 - (maxY - slot.getPositionY()));
                }
            }
            if(minY < 5){
                if(minY == slot.getPositionY()){
                    newPositionY = 5;
                }else {
                    newPositionY = (int) (5 + (slot.getPositionY() - minY));
                }
            }
            slot.setPositionX(newPositionX);
            slot.setPositionY(newPositionY);
            slot.changedParameters();
        }
        if(transform == "resizeTransformA") {


            if (slot instanceof TriangleSlot) {
                double Cx = slot.getPositionX() + (slot.getPositionX() + (slot.getWidth()/2) - slot.getPositionX()) * Math.cos(slot.getAngle()) -
                        (slot.getPositionY() + slot.getHeight() - slot.getPositionY()) * Math.sin(slot.getAngle());
                double Cy = slot.getPositionY() + (slot.getPositionX() + (slot.getWidth()/2) - slot.getPositionX()) * Math.sin(slot.getAngle()) +
                        (slot.getPositionY() + slot.getHeight() - slot.getPositionY()) * Math.cos(slot.getAngle());

                Cx = Math.round(Cx);
                Cy = Math.round(Cy);

                double nposx = Cx + (newPositionX - Cx) * Math.cos(slot.getAngle() * (-1)) - (newPositionY - Cy) * Math.sin(slot.getAngle() * (-1));
                double nposy = Cy + (newPositionX - Cx) * Math.sin(slot.getAngle() * (-1)) + (newPositionY - Cy) * Math.cos(slot.getAngle() * (-1));

                nposx = Math.round(nposx);
                nposy = Math.round(nposy);

                if (nposx > Cx) {
                    nposx = Cx - 5;
                }
                if (nposy > Cy) {
                    nposy = Cy - 5;
                }

                int newWidth = (int) (Cx - nposx);
                int newHeight = (int) (Cy - nposy);


                double Nmx = Math.round(Cx - (newWidth/2));
                double Nmy = Math.round(Cy - newHeight);


                double Mx = Cx + (Nmx - Cx) * Math.cos(slot.getAngle()) - (Nmy - Cy) * Math.sin(slot.getAngle());
                double My = Cy + (Nmx - Cx) * Math.sin(slot.getAngle()) + (Nmy - Cy) * Math.cos(slot.getAngle());

                Mx = Math.round(Mx);
                My = Math.round(My);

                double Ndx = nposx;
                double Ndy = Cy;

                double Dx = Cx + (Ndx - Cx) * Math.cos(slot.getAngle()) - (Ndy - Cy) * Math.sin(slot.getAngle());
                double Dy = Cy + (Ndx - Cx) * Math.sin(slot.getAngle()) + (Ndy - Cy) * Math.cos(slot.getAngle());

                Dx = Math.round(Dx);
                Dy = Math.round(Dy);


                if (Mx >= 500 || Mx <= 0 || My >= 500 || My <= 0) {
                    return;
                }
                if (Dx >= 500 || Dx <= 0 || Dy >= 500 || Dy <= 0) {
                    return;
                }

                slot.setWidth(newWidth);
                slot.setHeight(newHeight);
                slot.setPositionX((int) Mx);
                slot.setPositionY((int) My);
                slot.changedParameters();
            } else {
                double Cx = slot.getPositionX() + (slot.getPositionX() + slot.getWidth() - slot.getPositionX()) * Math.cos(slot.getAngle()) -
                        (slot.getPositionY() + slot.getHeight() - slot.getPositionY()) * Math.sin(slot.getAngle());
                double Cy = slot.getPositionY() + (slot.getPositionX() + slot.getWidth() - slot.getPositionX()) * Math.sin(slot.getAngle()) +
                        (slot.getPositionY() + slot.getHeight() - slot.getPositionY()) * Math.cos(slot.getAngle());

                Cx = Math.round(Cx);
                Cy = Math.round(Cy);

                double nposx = Cx + (newPositionX - Cx) * Math.cos(slot.getAngle() * (-1)) - (newPositionY - Cy) * Math.sin(slot.getAngle() * (-1));
                double nposy = Cy + (newPositionX - Cx) * Math.sin(slot.getAngle() * (-1)) + (newPositionY - Cy) * Math.cos(slot.getAngle() * (-1));

                nposx = Math.round(nposx);
                nposy = Math.round(nposy);

                if (nposx > Cx) {
                    nposx = Cx - 5;
                }
                if (nposy > Cy) {
                    nposy = Cy - 5;
                }

                int newWidth = (int) (Cx - nposx);
                int newHeight = (int) (Cy - nposy);

                double Ax = Cx + (nposx - Cx) * Math.cos(slot.getAngle()) - (nposy - Cy) * Math.sin(slot.getAngle());
                double Ay = Cy + (nposx - Cx) * Math.sin(slot.getAngle()) + (nposy - Cy) * Math.cos(slot.getAngle());

                Ax = Math.round(Ax);
                Ay = Math.round(Ay);

                double Nbx = nposx;
                double Nby = Cy;


                double Bx = Cx + (Nbx - Cx) * Math.cos(slot.getAngle()) - (Nby - Cy) * Math.sin(slot.getAngle());
                double By = Cy + (Nbx - Cx) * Math.sin(slot.getAngle()) + (Nby - Cy) * Math.cos(slot.getAngle());

                Bx = Math.round(Bx);
                By = Math.round(By);

                double Ndx = nposx;
                double Ndy = Cy;

                double Dx = Cx + (Ndx - Cx) * Math.cos(slot.getAngle()) - (Ndy - Cy) * Math.sin(slot.getAngle());
                double Dy = Cy + (Ndx - Cx) * Math.sin(slot.getAngle()) + (Ndy - Cy) * Math.cos(slot.getAngle());

                Dx = Math.round(Dx);
                Dy = Math.round(Dy);

                if (newPositionX >= 500 || newPositionX <= 0 || newPositionY >= 500 || newPositionY <= 0) {
                    return;
                }
                if (Bx >= 500 || Bx <= 0 || By >= 500 || By <= 0) {
                    return;
                }
                if (Dx >= 500 || Dx <= 0 || Dy >= 500 || Dy <= 0) {
                    return;
                }

                slot.setWidth(newWidth);
                slot.setHeight(newHeight);
                slot.setPositionX((int) Ax);
                slot.setPositionY((int) Ay);
                slot.changedParameters();
            }
        }
        if(transform == "resizeTransformB"){

            if(slot instanceof TriangleSlot){

                double Dx = slot.getPositionX() + (slot.getPositionX() - (slot.getWidth()/2) - slot.getPositionX()) * Math.cos(slot.getAngle()) -
                        (slot.getPositionY() + slot.getHeight() - slot.getPositionY()) * Math.sin(slot.getAngle());
                double Dy = slot.getPositionY() + (slot.getPositionX() - (slot.getWidth()/2) - slot.getPositionX()) * Math.sin(slot.getAngle()) +
                        (slot.getPositionY() + slot.getHeight() - slot.getPositionY()) * Math.cos(slot.getAngle());

                Dx = Math.round(Dx);
                Dy = Math.round(Dy);

                double nposx = Dx + (newPositionX - Dx) * Math.cos(slot.getAngle() * (-1)) - (newPositionY - Dy) * Math.sin(slot.getAngle() * (-1));
                double nposy = Dy + (newPositionX - Dx) * Math.sin(slot.getAngle() * (-1)) + (newPositionY - Dy) * Math.cos(slot.getAngle() * (-1));

                nposx = Math.round(nposx);
                nposy = Math.round(nposy);

                if (nposx < Dx) {
                    nposx = Dx + 5;
                }
                if (nposy > Dy) {
                    nposy = Dy - 5;
                }

                int newWidth = (int) (nposx - Dx);
                int newHeight = (int) (Dy - nposy);


                double Nmx = Math.round(Dx + (newWidth/2));
                double Nmy = Math.round(Dy - newHeight);


                double Mx = Dx + (Nmx - Dx) * Math.cos(slot.getAngle()) - (Nmy - Dy) * Math.sin(slot.getAngle());
                double My = Dy + (Nmx - Dx) * Math.sin(slot.getAngle()) + (Nmy - Dy) * Math.cos(slot.getAngle());

                Mx = Math.round(Mx);
                My = Math.round(My);

                double Ncx = Dx + newWidth;
                double Ncy = Dy;

                double Cx = Dx + (Ncx - Dx) * Math.cos(slot.getAngle()) - (Ncy - Dy) * Math.sin(slot.getAngle());
                double Cy = Dy + (Ncx - Dx) * Math.sin(slot.getAngle()) + (Ncy - Dy) * Math.cos(slot.getAngle());

                Cx = Math.round(Cx);
                Cy = Math.round(Cy);


                if (Mx >= 500 || Mx <= 0 || My >= 500 || My <= 0) {
                    return;
                }
                if (Cx >= 500 || Cx <= 0 || Cy >= 500 || Cy <= 0) {
                    return;
                }

                slot.setWidth(newWidth);
                slot.setHeight(newHeight);
                slot.setPositionX((int) Mx);
                slot.setPositionY((int) My);
                slot.changedParameters();
            }else {
                double Dx = slot.getPositionX() + (slot.getPositionX() - slot.getPositionX()) * Math.cos(slot.getAngle()) -
                        (slot.getPositionY() + slot.getHeight() - slot.getPositionY()) * Math.sin(slot.getAngle());
                double Dy = slot.getPositionY() + (slot.getPositionX() - slot.getPositionX()) * Math.sin(slot.getAngle()) +
                        (slot.getPositionY() + slot.getHeight() - slot.getPositionY()) * Math.cos(slot.getAngle());

                Dx = Math.round(Dx);
                Dy = Math.round(Dy);

                double nposx = Dx + (newPositionX - Dx) * Math.cos(slot.getAngle() * (-1)) - (newPositionY - Dy) * Math.sin(slot.getAngle() * (-1));
                double nposy = Dy + (newPositionX - Dx) * Math.sin(slot.getAngle() * (-1)) + (newPositionY - Dy) * Math.cos(slot.getAngle() * (-1));

                nposx = Math.round(nposx);
                nposy = Math.round(nposy);

                if (nposx < Dx) {
                    nposx = Dx + 5;
                }
                if (nposy > Dy) {
                    nposy = Dy - 5;
                }
                int newWidth = (int) (nposx - Dx);
                int newHeight = (int) (Dy - nposy);


                double Nax = Dx;
                double Nay = nposy;


                double Ax = Dx + (Nax - Dx) * Math.cos(slot.getAngle()) - (Nay - Dy) * Math.sin(slot.getAngle());
                double Ay = Dy + (Nax - Dx) * Math.sin(slot.getAngle()) + (Nay - Dy) * Math.cos(slot.getAngle());

                Ax = Math.round(Ax);
                Ay = Math.round(Ay);

                double Ncx = nposx;
                double Ncy = Dy;

                double Cx = Dx + (Ncx - Dx) * Math.cos(slot.getAngle()) - (Ncy - Dy) * Math.sin(slot.getAngle());
                double Cy = Dy + (Ncx - Dx) * Math.sin(slot.getAngle()) + (Ncy - Dy) * Math.cos(slot.getAngle());

                Cx = Math.round(Cx);
                Cy = Math.round(Cy);


                if (newPositionX >= 500 || newPositionX <= 0 || newPositionY >= 500 || newPositionY <= 0) {
                    return;
                }
                if (Ax >= 500 || Ax <= 0 || Ay >= 500 || Ay <= 0) {
                    return;
                }
                if (Cx >= 500 || Cx <= 0 || Cy >= 500 || Cy <= 0) {
                    return;
                }


                slot.setWidth(newWidth);
                slot.setHeight(newHeight);
                slot.setPositionX((int) Ax);
                slot.setPositionY((int) Ay);
                slot.changedParameters();
            }
        }
        if(transform == "resizeTransformC"){

            if(slot instanceof TriangleSlot){

                double Ax = slot.getPositionX() + (slot.getPositionX() - (slot.getWidth()/2) - slot.getPositionX()) * Math.cos(slot.getAngle()) -
                        (slot.getPositionY()  - slot.getPositionY()) * Math.sin(slot.getAngle());
                double Ay = slot.getPositionY() + (slot.getPositionX() - (slot.getWidth()/2) - slot.getPositionX()) * Math.sin(slot.getAngle()) +
                        (slot.getPositionY()  - slot.getPositionY()) * Math.cos(slot.getAngle());

                Ax = Math.round(Ax);
                Ay = Math.round(Ay);

                double nposx = Ax + (newPositionX - Ax) * Math.cos(slot.getAngle() * (-1)) - (newPositionY - Ay) * Math.sin(slot.getAngle() * (-1));
                double nposy = Ay + (newPositionX - Ax) * Math.sin(slot.getAngle() * (-1)) + (newPositionY - Ay) * Math.cos(slot.getAngle() * (-1));

                nposx = Math.round(nposx);
                nposy = Math.round(nposy);

                if (nposx < Ax) {
                    nposx = Ax + 5;
                }
                if (nposy < Ay) {
                    nposy = Ay + 5;
                }

                int newWidth = (int) (nposx - Ax);
                int newHeight = (int) (nposy - Ay);


                double Nmx = Math.round(Ax + (newWidth/2));
                double Nmy = Math.round(Ay);


                double Mx = Ax + (Nmx - Ax) * Math.cos(slot.getAngle()) - (Nmy - Ay) * Math.sin(slot.getAngle());
                double My = Ay + (Nmx - Ax) * Math.sin(slot.getAngle()) + (Nmy - Ay) * Math.cos(slot.getAngle());

                Mx = Math.round(Mx);
                My = Math.round(My);

                double Ndx = Ax;
                double Ndy = Ay + newHeight;

                double Dx = Ax + (Ndx - Ax) * Math.cos(slot.getAngle()) - (Ndy - Ay) * Math.sin(slot.getAngle());
                double Dy = Ay + (Ndx - Ax) * Math.sin(slot.getAngle()) + (Ndy - Ay) * Math.cos(slot.getAngle());

                Dx = Math.round(Dx);
                Dy = Math.round(Dy);

                if (newPositionX >= 500 || newPositionX <= 0 || newPositionY >= 500 || newPositionY <= 0) {
                    return;
                }
                if (Mx >= 500 || Mx <= 0 || My >= 500 || My <= 0) {
                    return;
                }
                if (Dx >= 500 || Dx <= 0 || Dy >= 500 || Dy <= 0) {
                    return;
                }

                slot.setWidth(newWidth);
                slot.setHeight(newHeight);
                slot.setPositionX((int) Mx);
                slot.setPositionY((int) My);
                slot.changedParameters();
            }else {
                double Ax = slot.getPositionX();
                double Ay = slot.getPositionY();


                double nposx = Ax + (newPositionX - Ax) * Math.cos(slot.getAngle() * (-1)) - (newPositionY - Ay) * Math.sin(slot.getAngle() * (-1));
                double nposy = Ay + (newPositionX - Ax) * Math.sin(slot.getAngle() * (-1)) + (newPositionY - Ay) * Math.cos(slot.getAngle() * (-1));

                nposx = Math.round(nposx);
                nposy = Math.round(nposy);

                if (nposx < Ax) {
                    nposx = Ax + 5;
                }
                if (nposy < Ay) {
                    nposy = Ay + 5;
                }

                int newWidth = (int) (nposx - Ax);
                int newHeight = (int) (nposy - Ay);


                double Nbx = nposx;
                double Nby = Ay;


                double Bx = Ax + (Nbx - Ax) * Math.cos(slot.getAngle()) - (Nby - Ay) * Math.sin(slot.getAngle());
                double By = Ay + (Nbx - Ax) * Math.sin(slot.getAngle()) + (Nby - Ay) * Math.cos(slot.getAngle());

                Bx = Math.round(Bx);
                By = Math.round(By);

                double Ndx = Ax;
                double Ndy = nposy;

                double Dx = Ax + (Ndx - Ax) * Math.cos(slot.getAngle()) - (Ndy - Ay) * Math.sin(slot.getAngle());
                double Dy = Ay + (Ndx - Ax) * Math.sin(slot.getAngle()) + (Ndy - Ay) * Math.cos(slot.getAngle());

                Dx = Math.round(Dx);
                Dy = Math.round(Dy);

                if (newPositionX >= 500 || newPositionX <= 0 || newPositionY >= 500 || newPositionY <= 0) {
                    return;
                }
                if (Bx >= 500 || Bx <= 0 || By >= 500 || By <= 0) {
                    return;
                }
                if (Dx >= 500 || Dx <= 0 || Dy >= 500 || Dy <= 0) {
                    return;
                }

                slot.setWidth(newWidth);
                slot.setHeight(newHeight);
                slot.setPositionX((int) Ax);
                slot.setPositionY((int) Ay);
                slot.changedParameters();
            }
        }
        if(transform == "resizeTransformD"){

            if(slot instanceof TriangleSlot){

                double Bx =  slot.getPositionX() + (slot.getPositionX()+(slot.getWidth()/2)-slot.getPositionX())*Math.cos(slot.getAngle()) -
                        (slot.getPositionY()-slot.getPositionY())*Math.sin(slot.getAngle());
                double By =  slot.getPositionY() + (slot.getPositionX()+(slot.getWidth()/2)-slot.getPositionX())*Math.sin(slot.getAngle()) +
                        (slot.getPositionY()-slot.getPositionY())*Math.cos(slot.getAngle());

                Bx = Math.round(Bx);
                By = Math.round(By);

                double nposx = Bx + (newPositionX - Bx) * Math.cos(slot.getAngle() * (-1)) - (newPositionY - By) * Math.sin(slot.getAngle() * (-1));
                double nposy = By + (newPositionX - Bx) * Math.sin(slot.getAngle() * (-1)) + (newPositionY - By) * Math.cos(slot.getAngle() * (-1));

                nposx = Math.round(nposx);
                nposy = Math.round(nposy);

                if (nposx > Bx) {
                    nposx = Bx - 5;
                }
                if (nposy < By) {
                    nposy = By + 5;
                }

                int newWidth = (int) (Bx - nposx);
                int newHeight = (int) (nposy - By);


                double Nmx = Math.round(Bx - (newWidth/2));
                double Nmy = Math.round(By);


                double Mx = Bx + (Nmx - Bx) * Math.cos(slot.getAngle()) - (Nmy - By) * Math.sin(slot.getAngle());
                double My = By + (Nmx - Bx) * Math.sin(slot.getAngle()) + (Nmy - By) * Math.cos(slot.getAngle());

                Mx = Math.round(Mx);
                My = Math.round(My);

                double Ncx = Bx;
                double Ncy = By + newHeight;

                double Cx = Bx + (Ncx - Bx) * Math.cos(slot.getAngle()) - (Ncy - By) * Math.sin(slot.getAngle());
                double Cy = By + (Ncx - Bx) * Math.sin(slot.getAngle()) + (Ncy - By) * Math.cos(slot.getAngle());

                Cx = Math.round(Cx);
                Cy = Math.round(Cy);

                if (newPositionX >= 500 || newPositionX <= 0 || newPositionY >= 500 || newPositionY <= 0) {
                    return;
                }
                if (Mx >= 500 || Mx <= 0 || My >= 500 || My <= 0) {
                    return;
                }
                if (Cx >= 500 || Cx <= 0 || Cy >= 500 || Cy <= 0) {
                    return;
                }

                slot.setWidth(newWidth);
                slot.setHeight(newHeight);
                slot.setPositionX((int) Mx);
                slot.setPositionY((int) My);
                slot.changedParameters();
            }else {
                double Bx = slot.getPositionX() + (slot.getPositionX() + slot.getWidth() - slot.getPositionX()) * Math.cos(slot.getAngle()) -
                        (slot.getPositionY() - slot.getPositionY()) * Math.sin(slot.getAngle());
                double By = slot.getPositionY() + (slot.getPositionX() + slot.getWidth() - slot.getPositionX()) * Math.sin(slot.getAngle()) +
                        (slot.getPositionY() - slot.getPositionY()) * Math.cos(slot.getAngle());

                Bx = Math.round(Bx);
                By = Math.round(By);

                double nposx = Bx + (newPositionX - Bx) * Math.cos(slot.getAngle() * (-1)) - (newPositionY - By) * Math.sin(slot.getAngle() * (-1));
                double nposy = By + (newPositionX - Bx) * Math.sin(slot.getAngle() * (-1)) + (newPositionY - By) * Math.cos(slot.getAngle() * (-1));

                nposx = Math.round(nposx);
                nposy = Math.round(nposy);

                if (nposx > Bx) {
                    nposx = Bx - 5;
                }
                if (nposy < By) {
                    nposy = By + 5;
                }
                int newWidth = (int) (Bx - nposx);
                int newHeight = (int) (nposy - By);


                double Nax = nposx;
                double Nay = By;


                double Ax = Bx + (Nax - Bx) * Math.cos(slot.getAngle()) - (Nay - By) * Math.sin(slot.getAngle());
                double Ay = By + (Nax - Bx) * Math.sin(slot.getAngle()) + (Nay - By) * Math.cos(slot.getAngle());

                Ax = Math.round(Ax);
                Ay = Math.round(Ay);

                double Ncx = Bx;
                double Ncy = nposy;

                double Cx = Bx + (Ncx - Bx) * Math.cos(slot.getAngle()) - (Ncy - By) * Math.sin(slot.getAngle());
                double Cy = By + (Ncx - Bx) * Math.sin(slot.getAngle()) + (Ncy - By) * Math.cos(slot.getAngle());

                Cx = Math.round(Cx);
                Cy = Math.round(Cy);


                if (newPositionX >= 500 || newPositionX <= 0 || newPositionY >= 500 || newPositionY <= 0) {
                    return;
                }
                if (Ax >= 500 || Ax <= 0 || Ay >= 500 || Ay <= 0) {
                    return;
                }
                if (Cx >= 500 || Cx <= 0 || Cy >= 500 || Cy <= 0) {
                    return;
                }


                slot.setWidth(newWidth);
                slot.setHeight(newHeight);
                slot.setPositionX((int) Ax);
                slot.setPositionY((int) Ay);
                slot.changedParameters();
            }
        }

        if(transform == "rotationTransform"){


            double minX;
            double maxX;
            double minY;
            double maxY;

            double newAngle = 0;

            if(slot instanceof TriangleSlot){
                double x1 = slot.getWidth()/2;
                double y1 = 0;
                double x2 = newPositionX - slot.getPositionX();
                double y2 = newPositionY - slot.getPositionY();
                double a = (x1*x2) + (y1*y2);
                double b = Math.sqrt(Math.pow(x1,2) + Math.pow(y1,2))*Math.sqrt(Math.pow(x2,2) + Math.pow(y2,2));
                newAngle = Math.acos(a/b);
                if(newPositionY < slot.getPositionY())
                    newAngle *= -1;


                double Mx = slot.getPositionX();
                double My = slot.getPositionY();
                double Dx = slot.getPositionX() + (slot.getPositionX() - (slot.getWidth()/2) - slot.getPositionX()) * Math.cos(newAngle) -
                        (slot.getPositionY() + slot.getHeight() - slot.getPositionY()) * Math.sin(newAngle);
                double Dy = slot.getPositionY() + (slot.getPositionX() - (slot.getWidth()/2) - slot.getPositionX()) * Math.sin(newAngle) +
                        (slot.getPositionY() + slot.getHeight() - slot.getPositionY()) * Math.cos(newAngle);

                double Cx = slot.getPositionX() + (slot.getPositionX() + (slot.getWidth()/2) - slot.getPositionX()) * Math.cos(newAngle) -
                        (slot.getPositionY() + slot.getHeight() - slot.getPositionY()) * Math.sin(newAngle);
                double Cy = slot.getPositionY() + (slot.getPositionX() + (slot.getWidth()/2) - slot.getPositionX()) * Math.sin(newAngle) +
                        (slot.getPositionY() + slot.getHeight() - slot.getPositionY()) * Math.cos(newAngle);
                maxX = Math.max(Mx, Math.max(Cx, Dx));
                minX = Math.min(Mx, Math.min(Cx, Dx));
                maxY = Math.max(My, Math.max(Cy, Dy));
                minY = Math.min(My, Math.min(Cy, Dy));

            }else {
                double x1 = slot.getWidth();
                double y1 = 0;
                double x2 = newPositionX - slot.getPositionX();
                double y2 = newPositionY - slot.getPositionY();
                double a = (x1*x2) + (y1*y2);
                double b = Math.sqrt(Math.pow(x1,2) + Math.pow(y1,2))*Math.sqrt(Math.pow(x2,2) + Math.pow(y2,2));
                newAngle = Math.acos(a/b);
                if(newPositionY < slot.getPositionY())
                    newAngle *= -1;

                double Bx = slot.getPositionX() + (slot.getPositionX() + slot.getWidth() - slot.getPositionX()) * Math.cos(newAngle) -
                        (slot.getPositionY() - slot.getPositionY()) * Math.sin(newAngle);
                double By = slot.getPositionY() + (slot.getPositionX() + slot.getWidth() - slot.getPositionX()) * Math.sin(newAngle) +
                        (slot.getPositionY() - slot.getPositionY()) * Math.cos(newAngle);

                double Dx = slot.getPositionX() + (slot.getPositionX() - slot.getPositionX()) * Math.cos(newAngle) -
                        (slot.getPositionY() + slot.getHeight() - slot.getPositionY()) * Math.sin(newAngle);
                double Dy = slot.getPositionY() + (slot.getPositionX() - slot.getPositionX()) * Math.sin(newAngle) +
                        (slot.getPositionY() + slot.getHeight() - slot.getPositionY()) * Math.cos(newAngle);

                double Cx = slot.getPositionX() + (slot.getPositionX() + slot.getWidth() - slot.getPositionX()) * Math.cos(newAngle) -
                        (slot.getPositionY() + slot.getHeight() - slot.getPositionY()) * Math.sin(newAngle);
                double Cy = slot.getPositionY() + (slot.getPositionX() + slot.getWidth() - slot.getPositionX()) * Math.sin(newAngle) +
                        (slot.getPositionY() + slot.getHeight() - slot.getPositionY()) * Math.cos(newAngle);


                maxX = Math.max(Math.max(slot.getPositionX(), Bx), Math.max(Cx, Dx));
                minX = Math.min(Math.min(slot.getPositionX(), Bx), Math.min(Cx, Dx));
                maxY = Math.max(Math.max(slot.getPositionY(), By), Math.max(Cy, Dy));
                minY = Math.min(Math.min(slot.getPositionY(), By), Math.min(Cy, Dy));

            }
            if(maxX >= 500){

                return;
            }
            if(minX <= 0){
                return;
            }
            if(maxY >= 500){
                return;
            }
            if(minY <= 0){
                return;
            }

            slot.setAngle(newAngle);
            slot.changedParameters();
        }

    }
    public boolean checkLimit(Slot slot,int positionX, int positionY){
        double maxX = 0;
        double maxY = 0;
        double minX = 0;
        double minY = 0;
        if(slot instanceof TriangleSlot){
            double Mx = positionX;
            double My = positionY;
            double Dx = positionX + (positionX - (slot.getWidth()/2) - positionX) * Math.cos(slot.getAngle()) -
                    (positionY + slot.getHeight() - positionY) * Math.sin(slot.getAngle());
            double Dy = positionY + (positionX - (slot.getWidth()/2) - positionX) * Math.sin(slot.getAngle()) +
                    (positionY + slot.getHeight() - positionY) * Math.cos(slot.getAngle());

            double Cx = positionX + (positionX + (slot.getWidth()/2) - positionX) * Math.cos(slot.getAngle()) -
                    (positionY + slot.getHeight() - positionY) * Math.sin(slot.getAngle());
            double Cy = positionY + (positionX + (slot.getWidth()/2) - positionX) * Math.sin(slot.getAngle()) +
                    (positionY + slot.getHeight() - positionY) * Math.cos(slot.getAngle());
            maxX = Math.max(Mx, Math.max(Cx, Dx));
            minX = Math.min(Mx, Math.min(Cx, Dx));
            maxY = Math.max(My, Math.max(Cy, Dy));
            minY = Math.min(My, Math.min(Cy, Dy));
        }else {
            double Bx = positionX + (positionX + slot.getWidth() - positionX) * Math.cos(slot.getAngle()) -
                    (positionY - positionY) * Math.sin(slot.getAngle());
            double By = positionY + (positionX + slot.getWidth() - positionX) * Math.sin(slot.getAngle()) +
                    (positionY - positionY) * Math.cos(slot.getAngle());

            double Dx = positionX + (positionX - positionX) * Math.cos(slot.getAngle()) -
                    (positionY + slot.getHeight() - positionY) * Math.sin(slot.getAngle());
            double Dy = positionY + (positionX - positionX) * Math.sin(slot.getAngle()) +
                    (positionY + slot.getHeight() - positionY) * Math.cos(slot.getAngle());

            double Cx = positionX + (positionX + slot.getWidth() - positionX) * Math.cos(slot.getAngle()) -
                    (positionY + slot.getHeight() - positionY) * Math.sin(slot.getAngle());
            double Cy = positionY + (positionX + slot.getWidth() - positionX) * Math.sin(slot.getAngle()) +
                    (positionY + slot.getHeight() - positionY) * Math.cos(slot.getAngle());

            maxX = Math.round(Math.max(Math.max(positionX, Bx), Math.max(Cx, Dx)));
            minX = Math.round(Math.min(Math.min(positionX, Bx), Math.min(Cx, Dx)));
            maxY = Math.max(Math.max(positionY, By), Math.max(Cy, Dy));
            minY = Math.min(Math.min(positionY, By), Math.min(Cy, Dy));

        }

        if(maxX >= 495){
            return true;
        }
        if(minX <= 5){
            return true;
        }
        if(maxY >= 495){
            return true;
        }
        if(minY <= 5){
            return true;
        }
        return false;
    }

    @Override
    public boolean checkAngle(Slot slot, double angle) {

        double minX;
        double maxX;
        double minY;
        double maxY;


        if(slot instanceof TriangleSlot){

            double Mx = slot.getPositionX();
            double My = slot.getPositionY();
            double Dx = slot.getPositionX() + (slot.getPositionX() - (slot.getWidth()/2) - slot.getPositionX()) * Math.cos(angle) -
                    (slot.getPositionY() + slot.getHeight() - slot.getPositionY()) * Math.sin(angle);
            double Dy = slot.getPositionY() + (slot.getPositionX() - (slot.getWidth()/2) - slot.getPositionX()) * Math.sin(angle) +
                    (slot.getPositionY() + slot.getHeight() - slot.getPositionY()) * Math.cos(angle);

            double Cx = slot.getPositionX() + (slot.getPositionX() + (slot.getWidth()/2) - slot.getPositionX()) * Math.cos(angle) -
                    (slot.getPositionY() + slot.getHeight() - slot.getPositionY()) * Math.sin(angle);
            double Cy = slot.getPositionY() + (slot.getPositionX() + (slot.getWidth()/2) - slot.getPositionX()) * Math.sin(angle) +
                    (slot.getPositionY() + slot.getHeight() - slot.getPositionY()) * Math.cos(angle);
            maxX = Math.max(Mx, Math.max(Cx, Dx));
            minX = Math.min(Mx, Math.min(Cx, Dx));
            maxY = Math.max(My, Math.max(Cy, Dy));
            minY = Math.min(My, Math.min(Cy, Dy));

        }else {


            double Bx = slot.getPositionX() + (slot.getPositionX() + slot.getWidth() - slot.getPositionX()) * Math.cos(angle) -
                    (slot.getPositionY() - slot.getPositionY()) * Math.sin(angle);
            double By = slot.getPositionY() + (slot.getPositionX() + slot.getWidth() - slot.getPositionX()) * Math.sin(angle) +
                    (slot.getPositionY() - slot.getPositionY()) * Math.cos(angle);

            double Dx = slot.getPositionX() + (slot.getPositionX() - slot.getPositionX()) * Math.cos(angle) -
                    (slot.getPositionY() + slot.getHeight() - slot.getPositionY()) * Math.sin(angle);
            double Dy = slot.getPositionY() + (slot.getPositionX() - slot.getPositionX()) * Math.sin(angle) +
                    (slot.getPositionY() + slot.getHeight() - slot.getPositionY()) * Math.cos(angle);

            double Cx = slot.getPositionX() + (slot.getPositionX() + slot.getWidth() - slot.getPositionX()) * Math.cos(angle) -
                    (slot.getPositionY() + slot.getHeight() - slot.getPositionY()) * Math.sin(angle);
            double Cy = slot.getPositionY() + (slot.getPositionX() + slot.getWidth() - slot.getPositionX()) * Math.sin(angle) +
                    (slot.getPositionY() + slot.getHeight() - slot.getPositionY()) * Math.cos(angle);


            maxX = Math.max(Math.max(slot.getPositionX(), Bx), Math.max(Cx, Dx));
            minX = Math.min(Math.min(slot.getPositionX(), Bx), Math.min(Cx, Dx));
            maxY = Math.max(Math.max(slot.getPositionY(), By), Math.max(Cy, Dy));
            minY = Math.min(Math.min(slot.getPositionY(), By), Math.min(Cy, Dy));

        }
        if(maxX >= 500){

            return true;
        }
        if(minX <= 0){
            return true;
        }
        if(maxY >= 500){
            return true;
        }
        if(minY <= 0){
            return true;
        }
        return false;
    }
}
