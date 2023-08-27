import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Comment } from 'src/app/models/comment';
import { Post } from 'src/app/models/post';
import { Rating } from 'src/app/models/rating';
import { Reaction } from 'src/app/models/reaction';
import { TypeReaction } from 'src/app/models/typeReaction';

@Injectable({
  providedIn: 'root'
})
export class PostService {
  posts: Post[] = [];
  
  constructor(private http: HttpClient) { }
  url="http://localhost:1111/welcometoesprit/api";


  getPosts() :Observable<Post[]> {
    return this.http.get<Post[]>(`${this.url}/post/posts/AllPost`); 

  }
  getPostById(id: number) {
    const url = `${this.url}/post/posts/${id}`;
    return this.http.get<Post>(url);
  }

  getCommentsByPostId(postId: number): Observable<any[]> {
    return this.http.get<Comment[]>(`${this.url}/comment/posts/${postId}/comments`);
  }
  addComment(postId: number, text: string): Observable<Comment> {
    
    const options = { withCredentials:true };
    return this.http.post<Comment>(`${this.url}/comment/posts/${postId}/comments/create?content=${text}`,options);
  }
  

  addPost(postContent: string): Observable<Post> {
    const params = new HttpParams().set('content', postContent);
    return this.http.post<Post>(`${this.url}/post/posts/create`, null, { params });
  }

addRating(postId: number,value: number):Observable<Rating>{
  const url =`${this.url}/rating/posts/${postId}/ratings/create?value=${value}`;
  return this.http.post<Rating>(url, null);}

  
getRating(postId: number)  {
  return this.http.get<Rating[]>(`${this.url}/rating/posts/${postId}/ratings`);
}

postPredict(){
  return this.http.get<any>(`${this.url}/post/predict`); 
}
postStat(){
  return this.http.get<any>(`${this.url}/post/post-count-by-period`); 
}
topRated(){
  return this.http.get<Post[]>(`${this.url}/post/posts/top-rated`); 
}


urllike="http://localhost:1111/welcometoesprit/api/reaction/comments/";
likeComment(commentId: number,type: TypeReaction){
  const options = { withCredentials:true };
return this.http.post<Reaction>(this.urllike+`${commentId}`+'/reactions/create?type='+`${type}`,options);
}



recherchePost(content: string, filter: string): Observable<Post[]> {
  const url = `${this.url}/post/recherchePost?Text=${content}&Filter=${filter}`;
  return this.http.get<Post[]>(url);
}








}
