package ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import model.User;
import service.UserService;
import service.PostService;
import model.Post;

public class MainMenu {
    private static UserService userService = new UserService();
    private static PostService postService = new PostService();
    private static User currentUser;
    static Map<Integer, String> postNumberToId = new HashMap<>();
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n1.Register 2.Login 3.View Profile 4.Create Post 5.View Feed 6.Like Post 7.Comment 0.Exit");
            int ch = Integer.parseInt(sc.nextLine());
            switch(ch) {
                case 1:
                    System.out.print("Username: "); String u = sc.nextLine();
                    System.out.print("Full Name: "); String n = sc.nextLine();
                    System.out.print("Email: "); String e = sc.nextLine();
                    System.out.print("Password: "); String p = sc.nextLine();
                    boolean reg = userService.register(u, n, e, p);
                    System.out.println(reg ? "Registered!" : "User/email exists!");
                    break;
                case 2:
                    System.out.print("Username: "); String lu = sc.nextLine();
                    System.out.print("Password: "); String lp = sc.nextLine();
                    User log = userService.login(lu, lp);
                    if (log == null) System.out.println("Invalid login");
                    else { currentUser = log; System.out.println("Welcome "+currentUser.getFullName()+"!"); }
                    break;
                case 3:
                    if (currentUser == null) System.out.println("You must log in to view your profile");
                    else System.out.println("Profile: "+currentUser.getUsername()+" "+currentUser.getFullName()+" "+currentUser.getEmail());
                    break;
                case 4:
                    if (currentUser == null) System.out.println("You must log in to create a post");
                    else {
                        System.out.print("Post content: "); String pc = sc.nextLine();
                        postService.createPost(currentUser.getId(), pc);
                        System.out.println("Posted!");
                    }
                    break;
                case 5:
                    if (currentUser == null)
                        System.out.println("You must log in to view the feed.");
                    else{
                        postNumberToId.clear();
                        int count = 1;
                    for (Post post : postService.getFeed(10)) {
                        String username = userService.getUsernameById(post.getAuthorId());
                        System.out.println("[" + post.getTimestamp() + "] " + username + ": " + post.getContent());
                        postNumberToId.put(count, post.getId());
                        count++;
                    }
                    }
                    break;
                case 6:
                    if (currentUser == null)
                        System.out.println("You must log in to like a post.");
                    else{
                        // Later, ask user to input post number for like and do:
                        System.out.print("Enter post number to like: ");
                        int postNum = sc.nextInt();
                        sc.nextLine(); // consume newline
                        String postId = postNumberToId.get(postNum);
                        if (postId == null)
                            System.out.println("Invalid post number!");
                        else{
                            postService.likePost(postId, currentUser.getId());
                            System.out.println("Liked!");
                            }
                    }
                    break;
                case 7:
                    if (currentUser == null)
                        System.out.println("You must log in to comment on a post.");
                    else{
                        System.out.print("Enter post number to comment: ");
                        int postNum = sc.nextInt();
                        sc.nextLine(); // consume newline

                        String postId = postNumberToId.get(postNum);
                        if (postId == null) {
                            System.out.println("Invalid post number!");
                        } else {
                            System.out.print("Your comment: "); String commentText = sc.nextLine();
                            postService.commentOnPost(postId, currentUser.getId(), commentText);
                        }
                    }
                    break;
                case 0: System.exit(0);
            }
        }
    }
}
