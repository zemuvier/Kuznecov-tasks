from django.conf.urls import url
from task2_app import views

urlpatterns = [
    url (r'^task2_app/$', views.snippet_list),
    url(r'^task2_app/(?P<pk>[0-9]+)/$', views.snippet_detail),
]
