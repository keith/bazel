// Copyright 2018 The Bazel Authors. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.google.devtools.build.lib.cmdline;

import com.google.devtools.build.lib.vfs.PathFragment;

/** Constants associated with {@code Label}s */
public class LabelConstants {
  /** The subdirectory under the output base which contains external repositories. */
  public static final PathFragment EXTERNAL_REPOSITORY_LOCATION = PathFragment.create("external");

  /** The new subdirectory under the output base which contains external repositories. */
  public static final PathFragment BAZEL_EXTERNAL_REPOSITORY_LOCATION =
      PathFragment.create("bazel-external");

  /**
   * The subdirectory under the output base which contains temporary working directories for module
   * extensions.
   */
  public static final PathFragment MODULE_EXTENSION_WORKING_DIRECTORY_LOCATION =
      PathFragment.create("modextwd");

  /**
   * The name of the package that contains the targets representing external repositories. Only
   * works if {@code --experimental_disable_external_package} is not in effect.
   */
  public static final PathFragment EXTERNAL_PACKAGE_NAME = PathFragment.create("external");

  /**
   * The identifier of the package that contains the targets representing external repositories.
   * Only works if {@code --experimental_disable_external_package} is not in effect.
   */
  public static final PackageIdentifier EXTERNAL_PACKAGE_IDENTIFIER =
      PackageIdentifier.createInMainRepo(EXTERNAL_PACKAGE_NAME);

  public static final PathFragment WORKSPACE_FILE_NAME = PathFragment.create("WORKSPACE");
  public static final PathFragment WORKSPACE_DOT_BAZEL_FILE_NAME =
      PathFragment.create("WORKSPACE.bazel");
  public static final PathFragment MODULE_DOT_BAZEL_FILE_NAME = PathFragment.create("MODULE.bazel");
  public static final PathFragment REPO_FILE_NAME = PathFragment.create("REPO.bazel");
  public static final PathFragment VENDOR_FILE_NAME = PathFragment.create("VENDOR.bazel");

  public static final PathFragment MODULE_LOCKFILE_NAME = PathFragment.create("MODULE.bazel.lock");

  // With this prefix, non-main repositories are symlinked under
  // $output_base/execution_root/__main__/external
  public static final PathFragment EXTERNAL_PATH_PREFIX = EXTERNAL_REPOSITORY_LOCATION;
  public static final PathFragment BAZEL_EXTERNAL_PATH_PREFIX = BAZEL_EXTERNAL_REPOSITORY_LOCATION;
  // With this prefix, non-main repositories are sibling symlinks of
  // $output_base/execution_root/__main__
  public static final PathFragment EXPERIMENTAL_EXTERNAL_PATH_PREFIX = PathFragment.create("..");

  // The relative path from the runfiles workspace root to external repository runfile top
  // directory.
  //
  // As a result, external repository runfiles are symlinked to:
  // $runfiles_root/$workspace_name/../$repo_name/<path>, i.e. $runfiles_root/$repo_name/<path>.
  public static final PathFragment EXTERNAL_RUNFILES_PATH_PREFIX = PathFragment.create("..");
  public static final String COMMAND_LINE_OPTION_PREFIX = "//command_line_option:";
  public static final PackageIdentifier COMMAND_LINE_OPTION_PACKAGE_IDENTIFIER =
      PackageIdentifier.createInMainRepo(PathFragment.create("command_line_option"));

  public static PathFragment getExternalRepositoryLocation(boolean useBazelExternalDirectory) {
    return useBazelExternalDirectory
        ? BAZEL_EXTERNAL_REPOSITORY_LOCATION
        : EXTERNAL_REPOSITORY_LOCATION;
  }

  public static PathFragment getExternalPathPrefix(boolean useBazelExternalDirectory) {
    return useBazelExternalDirectory ? BAZEL_EXTERNAL_PATH_PREFIX : EXTERNAL_PATH_PREFIX;
  }

  public static PathFragment getExternalPathPrefix(
      boolean siblingRepositoryLayout, boolean useBazelExternalDirectory) {
    return siblingRepositoryLayout
        ? EXPERIMENTAL_EXTERNAL_PATH_PREFIX
        : getExternalPathPrefix(useBazelExternalDirectory);
  }

  public static boolean isExternalPathPrefix(PathFragment path) {
    return path.equals(EXTERNAL_PATH_PREFIX) || path.equals(BAZEL_EXTERNAL_PATH_PREFIX);
  }

  public static PathFragment getExternalPathPrefix(PathFragment path) {
    if (path.startsWith(BAZEL_EXTERNAL_PATH_PREFIX)) {
      return BAZEL_EXTERNAL_PATH_PREFIX;
    }
    if (path.startsWith(EXTERNAL_PATH_PREFIX)) {
      return EXTERNAL_PATH_PREFIX;
    }
    return null;
  }
}
