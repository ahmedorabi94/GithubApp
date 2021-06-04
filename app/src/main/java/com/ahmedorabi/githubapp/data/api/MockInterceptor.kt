package com.ahmedorabi.githubapp.data.api

import com.ahmedorabi.githubapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody
import timber.log.Timber

class MockInterceptor : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        if (BuildConfig.DEBUG) {
            val uri = chain.request().url.toUri().toString()
            val responseString = when {
                uri.endsWith("movies") -> getListOfReposBeingStarredJson
                else -> ""
            }

            Timber.e(responseString)

            return chain.proceed(chain.request())
                .newBuilder()
                .code(200)
                .protocol(Protocol.HTTP_2)
                .message(responseString)
                .body(
                    ResponseBody.create(
                        "application/json".toMediaTypeOrNull(),
                        responseString.toByteArray()
                    )
                )
                .addHeader("content-type", "application/json")
                //  .addHeader()
                .build()
        } else {
            //just to be on safe side.
            throw IllegalAccessError(
                "MockInterceptor is only meant for Testing Purposes and " +
                        "bound to be used only with DEBUG mode"
            )
        }
    }

}

const val getListOfReposBeingStarredJson = """
{
  "total_count": 2,
  "incomplete_results": false,
  "items": [
    {
      "id": 30197168,
      "node_id": "MDEwOlJlcG9zaXRvcnkzMDE5NzE2OA==",
      "name": "movies",
      "full_name": "KMindeguia/movies",
      "private": false,
      "owner": {
        "login": "KMindeguia",
        "id": 480064,
        "node_id": "MDQ6VXNlcjQ4MDA2NA==",
        "avatar_url": "https://avatars.githubusercontent.com/u/480064?v=4",
        "gravatar_id": "",
        "url": "https://api.github.com/users/KMindeguia",
        "html_url": "https://github.com/KMindeguia",
        "followers_url": "https://api.github.com/users/KMindeguia/followers",
        "following_url": "https://api.github.com/users/KMindeguia/following{/other_user}",
        "gists_url": "https://api.github.com/users/KMindeguia/gists{/gist_id}",
        "starred_url": "https://api.github.com/users/KMindeguia/starred{/owner}{/repo}",
        "subscriptions_url": "https://api.github.com/users/KMindeguia/subscriptions",
        "organizations_url": "https://api.github.com/users/KMindeguia/orgs",
        "repos_url": "https://api.github.com/users/KMindeguia/repos",
        "events_url": "https://api.github.com/users/KMindeguia/events{/privacy}",
        "received_events_url": "https://api.github.com/users/KMindeguia/received_events",
        "type": "User",
        "site_admin": false
      },
      "html_url": "https://github.com/KMindeguia/movies",
      "description": null,
      "fork": false,
      "url": "https://api.github.com/repos/KMindeguia/movies",
      "forks_url": "https://api.github.com/repos/KMindeguia/movies/forks",
      "keys_url": "https://api.github.com/repos/KMindeguia/movies/keys{/key_id}",
      "collaborators_url": "https://api.github.com/repos/KMindeguia/movies/collaborators{/collaborator}",
      "teams_url": "https://api.github.com/repos/KMindeguia/movies/teams",
      "hooks_url": "https://api.github.com/repos/KMindeguia/movies/hooks",
      "issue_events_url": "https://api.github.com/repos/KMindeguia/movies/issues/events{/number}",
      "events_url": "https://api.github.com/repos/KMindeguia/movies/events",
      "assignees_url": "https://api.github.com/repos/KMindeguia/movies/assignees{/user}",
      "branches_url": "https://api.github.com/repos/KMindeguia/movies/branches{/branch}",
      "tags_url": "https://api.github.com/repos/KMindeguia/movies/tags",
      "blobs_url": "https://api.github.com/repos/KMindeguia/movies/git/blobs{/sha}",
      "git_tags_url": "https://api.github.com/repos/KMindeguia/movies/git/tags{/sha}",
      "git_refs_url": "https://api.github.com/repos/KMindeguia/movies/git/refs{/sha}",
      "trees_url": "https://api.github.com/repos/KMindeguia/movies/git/trees{/sha}",
      "statuses_url": "https://api.github.com/repos/KMindeguia/movies/statuses/{sha}",
      "languages_url": "https://api.github.com/repos/KMindeguia/movies/languages",
      "stargazers_url": "https://api.github.com/repos/KMindeguia/movies/stargazers",
      "contributors_url": "https://api.github.com/repos/KMindeguia/movies/contributors",
      "subscribers_url": "https://api.github.com/repos/KMindeguia/movies/subscribers",
      "subscription_url": "https://api.github.com/repos/KMindeguia/movies/subscription",
      "commits_url": "https://api.github.com/repos/KMindeguia/movies/commits{/sha}",
      "git_commits_url": "https://api.github.com/repos/KMindeguia/movies/git/commits{/sha}",
      "comments_url": "https://api.github.com/repos/KMindeguia/movies/comments{/number}",
      "issue_comment_url": "https://api.github.com/repos/KMindeguia/movies/issues/comments{/number}",
      "contents_url": "https://api.github.com/repos/KMindeguia/movies/contents/{+path}",
      "compare_url": "https://api.github.com/repos/KMindeguia/movies/compare/{base}...{head}",
      "merges_url": "https://api.github.com/repos/KMindeguia/movies/merges",
      "archive_url": "https://api.github.com/repos/KMindeguia/movies/{archive_format}{/ref}",
      "downloads_url": "https://api.github.com/repos/KMindeguia/movies/downloads",
      "issues_url": "https://api.github.com/repos/KMindeguia/movies/issues{/number}",
      "pulls_url": "https://api.github.com/repos/KMindeguia/movies/pulls{/number}",
      "milestones_url": "https://api.github.com/repos/KMindeguia/movies/milestones{/number}",
      "notifications_url": "https://api.github.com/repos/KMindeguia/movies/notifications{?since,all,participating}",
      "labels_url": "https://api.github.com/repos/KMindeguia/movies/labels{/name}",
      "releases_url": "https://api.github.com/repos/KMindeguia/movies/releases{/id}",
      "deployments_url": "https://api.github.com/repos/KMindeguia/movies/deployments",
      "created_at": "2015-02-02T16:50:32Z",
      "updated_at": "2021-05-26T07:53:51Z",
      "pushed_at": "2019-01-15T13:41:03Z",
      "git_url": "git://github.com/KMindeguia/movies.git",
      "ssh_url": "git@github.com:KMindeguia/movies.git",
      "clone_url": "https://github.com/KMindeguia/movies.git",
      "svn_url": "https://github.com/KMindeguia/movies",
      "homepage": "",
      "size": 23789,
      "stargazers_count": 861,
      "watchers_count": 861,
      "language": "Objective-C",
      "has_issues": true,
      "has_projects": true,
      "has_downloads": true,
      "has_wiki": true,
      "has_pages": false,
      "forks_count": 162,
      "mirror_url": null,
      "archived": false,
      "disabled": false,
      "open_issues_count": 0,
      "license": {
        "key": "mit",
        "name": "MIT License",
        "spdx_id": "MIT",
        "url": "https://api.github.com/licenses/mit",
        "node_id": "MDc6TGljZW5zZTEz"
      },
      "forks": 162,
      "open_issues": 0,
      "watchers": 861,
      "default_branch": "master",
      "score": 1
    },
    {
      "id": 82468492,
      "node_id": "MDEwOlJlcG9zaXRvcnk4MjQ2ODQ5Mg==",
      "name": "movies-for-hackers",
      "full_name": "k4m4/movies-for-hackers",
      "private": false,
      "owner": {
        "login": "k4m4",
        "id": 8945656,
        "node_id": "MDQ6VXNlcjg5NDU2NTY=",
        "avatar_url": "https://avatars.githubusercontent.com/u/8945656?v=4",
        "gravatar_id": "",
        "url": "https://api.github.com/users/k4m4",
        "html_url": "https://github.com/k4m4",
        "followers_url": "https://api.github.com/users/k4m4/followers",
        "following_url": "https://api.github.com/users/k4m4/following{/other_user}",
        "gists_url": "https://api.github.com/users/k4m4/gists{/gist_id}",
        "starred_url": "https://api.github.com/users/k4m4/starred{/owner}{/repo}",
        "subscriptions_url": "https://api.github.com/users/k4m4/subscriptions",
        "organizations_url": "https://api.github.com/users/k4m4/orgs",
        "repos_url": "https://api.github.com/users/k4m4/repos",
        "events_url": "https://api.github.com/users/k4m4/events{/privacy}",
        "received_events_url": "https://api.github.com/users/k4m4/received_events",
        "type": "User",
        "site_admin": false
      },
      "html_url": "https://github.com/k4m4/movies-for-hackers",
      "description": "🎬 A curated list of movies every hacker & cyberpunk must watch.",
      "fork": false,
      "url": "https://api.github.com/repos/k4m4/movies-for-hackers",
      "forks_url": "https://api.github.com/repos/k4m4/movies-for-hackers/forks",
      "keys_url": "https://api.github.com/repos/k4m4/movies-for-hackers/keys{/key_id}",
      "collaborators_url": "https://api.github.com/repos/k4m4/movies-for-hackers/collaborators{/collaborator}",
      "teams_url": "https://api.github.com/repos/k4m4/movies-for-hackers/teams",
      "hooks_url": "https://api.github.com/repos/k4m4/movies-for-hackers/hooks",
      "issue_events_url": "https://api.github.com/repos/k4m4/movies-for-hackers/issues/events{/number}",
      "events_url": "https://api.github.com/repos/k4m4/movies-for-hackers/events",
      "assignees_url": "https://api.github.com/repos/k4m4/movies-for-hackers/assignees{/user}",
      "branches_url": "https://api.github.com/repos/k4m4/movies-for-hackers/branches{/branch}",
      "tags_url": "https://api.github.com/repos/k4m4/movies-for-hackers/tags",
      "blobs_url": "https://api.github.com/repos/k4m4/movies-for-hackers/git/blobs{/sha}",
      "git_tags_url": "https://api.github.com/repos/k4m4/movies-for-hackers/git/tags{/sha}",
      "git_refs_url": "https://api.github.com/repos/k4m4/movies-for-hackers/git/refs{/sha}",
      "trees_url": "https://api.github.com/repos/k4m4/movies-for-hackers/git/trees{/sha}",
      "statuses_url": "https://api.github.com/repos/k4m4/movies-for-hackers/statuses/{sha}",
      "languages_url": "https://api.github.com/repos/k4m4/movies-for-hackers/languages",
      "stargazers_url": "https://api.github.com/repos/k4m4/movies-for-hackers/stargazers",
      "contributors_url": "https://api.github.com/repos/k4m4/movies-for-hackers/contributors",
      "subscribers_url": "https://api.github.com/repos/k4m4/movies-for-hackers/subscribers",
      "subscription_url": "https://api.github.com/repos/k4m4/movies-for-hackers/subscription",
      "commits_url": "https://api.github.com/repos/k4m4/movies-for-hackers/commits{/sha}",
      "git_commits_url": "https://api.github.com/repos/k4m4/movies-for-hackers/git/commits{/sha}",
      "comments_url": "https://api.github.com/repos/k4m4/movies-for-hackers/comments{/number}",
      "issue_comment_url": "https://api.github.com/repos/k4m4/movies-for-hackers/issues/comments{/number}",
      "contents_url": "https://api.github.com/repos/k4m4/movies-for-hackers/contents/{+path}",
      "compare_url": "https://api.github.com/repos/k4m4/movies-for-hackers/compare/{base}...{head}",
      "merges_url": "https://api.github.com/repos/k4m4/movies-for-hackers/merges",
      "archive_url": "https://api.github.com/repos/k4m4/movies-for-hackers/{archive_format}{/ref}",
      "downloads_url": "https://api.github.com/repos/k4m4/movies-for-hackers/downloads",
      "issues_url": "https://api.github.com/repos/k4m4/movies-for-hackers/issues{/number}",
      "pulls_url": "https://api.github.com/repos/k4m4/movies-for-hackers/pulls{/number}",
      "milestones_url": "https://api.github.com/repos/k4m4/movies-for-hackers/milestones{/number}",
      "notifications_url": "https://api.github.com/repos/k4m4/movies-for-hackers/notifications{?since,all,participating}",
      "labels_url": "https://api.github.com/repos/k4m4/movies-for-hackers/labels{/name}",
      "releases_url": "https://api.github.com/repos/k4m4/movies-for-hackers/releases{/id}",
      "deployments_url": "https://api.github.com/repos/k4m4/movies-for-hackers/deployments",
      "created_at": "2017-02-19T15:24:42Z",
      "updated_at": "2021-06-04T13:00:48Z",
      "pushed_at": "2021-05-14T06:53:51Z",
      "git_url": "git://github.com/k4m4/movies-for-hackers.git",
      "ssh_url": "git@github.com:k4m4/movies-for-hackers.git",
      "clone_url": "https://github.com/k4m4/movies-for-hackers.git",
      "svn_url": "https://github.com/k4m4/movies-for-hackers",
      "homepage": "https://hackermovie.club/",
      "size": 286,
      "stargazers_count": 8682,
      "watchers_count": 8682,
      "language": "Shell",
      "has_issues": true,
      "has_projects": true,
      "has_downloads": true,
      "has_wiki": true,
      "has_pages": true,
      "forks_count": 818,
      "mirror_url": null,
      "archived": false,
      "disabled": false,
      "open_issues_count": 125,
      "license": {
        "key": "cc0-1.0",
        "name": "Creative Commons Zero v1.0 Universal",
        "spdx_id": "CC0-1.0",
        "url": "https://api.github.com/licenses/cc0-1.0",
        "node_id": "MDc6TGljZW5zZTY="
      },
      "forks": 818,
      "open_issues": 125,
      "watchers": 8682,
      "default_branch": "master",
      "score": 1
    }
  ]
}
"""