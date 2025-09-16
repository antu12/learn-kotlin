package com.antu.features.blogs

import io.ktor.resources.Resource
import io.ktor.server.resources.delete
import io.ktor.server.resources.get
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get

fun Route.blogRoutes(){
    get("/blogs/{id}"){
        val id = call.pathParameters["id"]
        val q1 = call.queryParameters["q1"]
        call.respondText { "Blog with id: $id and query: $q1" }
    }
    get<Blogs>{ blogs ->
        val sort = blogs.sort
        call.respondText { "sort order is: $sort" }
    }
    delete<Blogs.Blog>{ blogs ->
        val id = blogs.id
        val sort = blogs.parent.sort
        call.respondText { "Blog id is: $id and sort is: $sort" }
    }
}

@Resource("blogs")
class Blogs(val sort: String = "new"){
    @Resource("{id}")
    data class Blog(val parent: Blogs = Blogs(), val id: String)
}