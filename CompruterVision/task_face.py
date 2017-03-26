import cv2

import base


def get_faces(image):
    faceCascade = cv2.CascadeClassifier("haarcascade_frontalface_alt.xml")
    gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
    faces = faceCascade.detectMultiScale(gray, scaleFactor=1.25,
                                         minNeighbors=2, minSize=(30, 30),
                                         flags=3)
    print "Found %s faces!" % len(faces)
    for (x, y, w, h) in faces:
        cv2.rectangle(image, (x, y), (x+w, y+h), (0, 255, 0), 2)
    base.display_image(image, 'Faces')
    base.save_image(image=image, image_name='final')

if __name__ == '__main__':
    image = base.read_image("people.jpg")
    get_faces(image)
